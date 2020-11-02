package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Course;
import org.elective.service.CoursesService;
import org.elective.service.LocalizedTextSupplier;
import org.elective.service.converters.CourseConverter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/courses/{subject}/course_{id:\\d+}")
public class CourseController {
    private static final String COURSE_PAGE_NAME = "course";
    private static final String COURSE_OBJECT = "course";

    private final CoursesService coursesService;
    private final CourseConverter courseConverter;
    private final LocalizedTextSupplier localizedTextSupplier;

    @RequestMapping
    public String coursePage(@RequestParam(required = false) Optional<String> cancel,
                             @RequestParam(required = false) Optional<String> register,
                             @PathVariable("subject") String subject,
                             @PathVariable("id") Long id,
                             @PathVariable String locale,
                             Map<String, Object> model) {
        log.info("subject by ID ({})", id);
        if (subject == null || subject.isEmpty())
            return coursesService.errorPage(model, "Page not fond");
        Optional<Course> course = coursesService.getCourseById(id);
        if (!course.isPresent())
            return coursesService.errorPage(model, "Course not fond");
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
        coursesService.addMessageIfExistParam(model,
                localizedTextSupplier.getLocalizedText("courses.canceled", locale),
                cancel);
        coursesService.addMessageIfExistParam(model,
                localizedTextSupplier.getLocalizedText("courses.registered", locale),
                register);
        return COURSE_PAGE_NAME;
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

    @ModelAttribute
    public void preparePage(@PathVariable String locale,
                            HttpServletRequest request,
                            Map<String, Object> model) {
        coursesService.prepareAllStaticOnPage(model, locale);
        String uri = request.getRequestURI();
        model.put("uri", uri);
    }
}
