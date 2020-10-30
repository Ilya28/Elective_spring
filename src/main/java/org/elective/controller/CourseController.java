package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Course;
import org.elective.service.CoursesService;
import org.elective.service.LocalizedTextSupplier;
import org.elective.service.RegistrationsService;
import org.elective.service.SubjectsService;
import org.elective.service.converters.CourseConverter;
import org.elective.service.preparing.PagePrepareService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/courses/{subject}/course_{id:\\d+}")
public class CourseController {
    private static final String COURSES_PAGE_NAME = "courses";
    private static final String COURSE_PAGE_NAME = "course";
    private static final String COURSES_LIST = "courses";
    private static final String COURSE_OBJECT = "course";
    private static final String MESSAGE_HOLDER = "message";

    private final CoursesService coursesService;
    private final CourseConverter courseConverter;
    private  final RegistrationsService registrationsService;
    private final PagePrepareService pagePrepareService;
    private final LocalizedTextSupplier localizedTextSupplier;

    private String errorPage(Map<String, Object> model, String errorText) {
        log.debug("Error page: {}", errorText);
        model.put("error_message", errorText);
        return "my_error";
    }

    @RequestMapping
    public String coursePage(@RequestParam(required = false) Optional<String> cancel,
                             @RequestParam(required = false) Optional<String> register,
                             @PathVariable("subject") String subject,
                             @PathVariable("id") Long id,
                             @PathVariable String locale,
                             Map<String, Object> model) {
        log.info("subject by ID ({})", id);
        pagePrepareService.prepareAllStaticOnPage(model, locale);
        if (subject == null || subject.isEmpty())
            return errorPage(model, "Page not fond");
        Optional<Course> course = coursesService.getCourseById(id);
        if (!course.isPresent())
            return errorPage(model, "Course not fond");
        if (!subject.equals(course.get().getSubject().getMapping())) {
            log.debug("Page must be redirected (this course expected another subject)");
            return coursesService.getCourseRedirect(course.get());
        }
        log.info("return course: {}", course.get());
        model.put(COURSE_OBJECT, courseConverter.courseToCourseDTO(course.get(), locale));
        model.put("status", coursesService.getCourseStatus(course.get()));
        model.put("registered", coursesService.isUserRegisteredForCourse(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                id
        ));
        pagePrepareService.addMessageIfExistParam(model,
                localizedTextSupplier.getLocalizedText("courses.canceled", locale),
                cancel);
        pagePrepareService.addMessageIfExistParam(model,
                localizedTextSupplier.getLocalizedText("courses.registered", locale),
                register);
        return COURSE_PAGE_NAME;
    }

    @RequestMapping("/register")
    public String courseRegister(@PathVariable("subject") String subject,
                                 @PathVariable("id") Long id,
                                 @PathVariable String locale,
                                 Map<String, Object> model) {
        log.info("Register to course with ID ({})", id);
        registrationsService.registerByEmailAndCourseId(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                id
        );
        return "refirect:/" + locale + "/courses/" + subject + "/course_" + id + "?register";
    }

    @Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
    @RequestMapping("/delete")
    public String courseDelete(@PathVariable("subject") String subject,
                               @PathVariable("id") Long id,
                               @PathVariable String locale,
                               Map<String, Object> model) {
        log.info("Delete course by ID ({})", id);
        coursesService.deleteCourseById(id);
        return "redirect:/" + locale + "/courses/" + subject;
    }
}
