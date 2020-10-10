package org.elective.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Course;
import org.elective.entity.Registration;
import org.elective.entity.User;
import org.elective.repos.CourseRepo;
import org.elective.repos.SubjectRepo;
import org.elective.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:en|ua}/users")
public class UsersController {
    public static final String USERS_LIST = "users";
    public static final String PAGE_NAME = "users";

    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;

    @RequestMapping
    public String usersPage(@RequestParam(defaultValue = "1") Integer page /* TODO: Pagination */,
                              @RequestParam(required = false) String filterByCourse,
                              @RequestParam(required = false) String filterBySubject,
                              Map<String, Object> model) {
        model.put("page_count", 5); /* TODO: Pagination */
        model.put("page", page); /* TODO: Pagination */
        if (filterByCourse == null || filterByCourse.isEmpty()) {
            model.put(USERS_LIST, userRepo.findAll());
            return PAGE_NAME;
        }
        Optional<Course> course = courseRepo.findCourseByNameEN(filterByCourse);
        if (course.isPresent()) {
            List<User> usersByCourse = course.get().getRegistrationSet().stream()
                    .map(Registration::getUser)
                    .collect(Collectors.toList());
            model.put(USERS_LIST, usersByCourse);
        } else {
            model.put(USERS_LIST, Collections.emptyList());
        }
        return PAGE_NAME;
    }
}
