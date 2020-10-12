package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Course;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoursesService {
    public String getCoursePath(Course course) {
        return course.getSubject().getNameEN() + "/course_" + course.getId();
    }
    public String getCourseRedirect(Course course) {
        return "redirect:/" + getCoursePath(course);
    }
}
