package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.entity.Course;
import org.elective.entity.Subject;
import org.elective.entity.User;
import org.elective.repos.CourseRepo;
import org.elective.repos.SubjectRepo;
import org.elective.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    public static final String MAIN_PAGE_NAME = "index";

    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;

    @RequestMapping("/")
    public String mainPage(Map<String, Object> model){
        log.info("{Main page}");
        return MAIN_PAGE_NAME;
    }
}
