package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Course;
import org.elective.entity.Subject;
import org.elective.repos.CourseRepo;
import org.elective.repos.SubjectRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/courses")
public class CoursesController {
    public static final String PAGE_NAME = "courses";
    public static final String COURSES_LIST = "users";

    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;

    private String errorPage(Map<String, Object> model, String errorText) {
        model.put("error_message", errorText);
        return "error";
    }

    @RequestMapping
    public String coursesPage(Map<String, Object> model) {
        Iterable<Course> courses = courseRepo.findAll();
        model.put(COURSES_LIST, courses);
        return PAGE_NAME;
    }

    @RequestMapping("/{subject}")
    public String coursesBySubjectPage(@PathVariable("subject") String subject, Map<String, Object> model) {
        if (subject == null || subject.isEmpty())
            return errorPage(model, "Page not fond");
        Optional<Subject> subjectInDB = subjectRepo.findSubjectByNameEN(subject);
        if (!subjectInDB.isPresent())
            return errorPage(model, "The subject not fond");
        Iterable<Course> courses = courseRepo.findCoursesBySubjectNameEN(subject);
        model.put(COURSES_LIST, courses);
        return PAGE_NAME;
    }

    @RequestMapping("/{subject}/course_{id:\\d+}")
    public String coursePage(@PathVariable("subject") String subject, @PathVariable("id") Long id, Map<String, Object> model) {
        if (subject == null || subject.isEmpty())
            return errorPage(model, "Page not fond");
        Optional<Course> course = courseRepo.findById(id);
        if (!course.isPresent())
            return errorPage(model, "Course not fond");
        if (!subject.equals(course.get().getSubject().getNameEN()))
            return "redirect:/" + course.get().getSubject().getNameEN() + "/course_" + id;
        model.put(COURSES_LIST, course);
        return PAGE_NAME;
    }
}
