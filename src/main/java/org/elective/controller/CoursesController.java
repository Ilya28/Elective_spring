package org.elective.controller;

import org.elective.domain.dto.Course;
import org.elective.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CoursesController {
    @Autowired
    CourseRepo courseRepo;

    @RequestMapping("/courses")
    public String coursesPage(@RequestParam(required = false) String subject, Map<String, Object> model) {
        if (subject!= null && !subject.isEmpty()) {
            Iterable<Course> courses = courseRepo.findAll();
            model.put("courses", courses);
        }
        return "courses";
    }
}
