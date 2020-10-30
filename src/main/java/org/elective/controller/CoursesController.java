package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.CourseDTO;
import org.elective.dto.UserDTO;
import org.elective.entity.Course;
import org.elective.repos.CourseRepo;
import org.elective.repos.SubjectRepo;
import org.elective.service.CoursesService;
import org.elective.service.LocalizedTextSupplier;
import org.elective.service.SubjectsService;
import org.elective.service.converters.CourseConverter;
import org.elective.service.preparing.PagePrepareService;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/courses")
public class CoursesController {
    private static final String COURSES_PAGE_NAME = "courses";
    private static final String COURSES_LIST = "courses";
    private static final String MESSAGE_HOLDER = "message";

    private final CoursesService coursesService;
    private  final SubjectsService subjectsService;
    private final PagePrepareService pagePrepareService;
    private final LocalizedTextSupplier localizedTextSupplier;

    private String errorPage(Map<String, Object> model, String errorText) {
        log.debug("Error page: {}", errorText);
        model.put("error_message", errorText);
        return "my_error";
    }

    @RequestMapping
    public String coursesPage(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                              Map<String, Object> model,
                              @PathVariable String locale) {
        log.info("All courses page");
        pagePrepareService.prepareAllStaticOnPage(model, locale);
        model.put("url", "/" + locale + "/courses");
        model.put("subject", localizedTextSupplier.getLocalizedText("courses.all_courses", locale));
        model.put(COURSES_LIST, coursesService.getAllCoursesLocalized(locale, pageable));
        return COURSES_PAGE_NAME;
    }

    @RequestMapping("/{subject}")
    public String coursesBySubjectPage(@RequestParam(required = false) Optional<String> added,
                                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                       @PathVariable("subject") String subject,
                                       @PathVariable String locale,
                                       Map<String, Object> model) {
        log.info("Courses page. Subject = {}", subject);
        pagePrepareService.prepareAllStaticOnPage(model, locale);
        model.put("subject", subjectsService.getSubjectName(subject, locale));
        if (subject == null || subject.isEmpty())
            return errorPage(model, "Page not fond");
        if (!subjectsService.exist(subject))
            return errorPage(model, "The subject not fond");
        Page<CourseDTO> courses = coursesService.findCoursesBySubjectMappingLocalized(subject, locale, pageable);
        added.ifPresent(s -> model.put(MESSAGE_HOLDER, "Added successful"));
        model.put("url", "/" + locale + "/courses/" + subject);
        model.put("add_url", "/" + locale + "/courses/" + subject + "/add");
        model.put("course_url_pattern", "/" + locale + "/courses/" + subject + "/course_");
        model.put(COURSES_LIST, courses);
        return COURSES_PAGE_NAME;
    }

    @Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @PostMapping("/{subject}/add")
    public String addCourse(@ModelAttribute CourseDTO course,
                            @PathVariable("subject") String subject,
                            @PathVariable String locale,
                            Map<String, Object> model) {
        log.info("Course add page");
        if (!subjectsService.exist(subject)) {
            log.debug("Can't find this subject: {}", subject);
            return errorPage(model, "This course dose not exist");
        }
        log.info("New course: {}", course);
        coursesService.saveCourse(course, subject);
        return "redirect:/" + locale + "/courses/" + subject + "?added";
    }
}
