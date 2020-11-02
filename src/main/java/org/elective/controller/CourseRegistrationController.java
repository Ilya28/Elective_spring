package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.service.RegistrationsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/courses/{subject}/course_{id:\\d+}")
public class CourseRegistrationController {
    private  final RegistrationsService registrationsService;

    @RequestMapping("/register")
    public String courseRegister(@PathVariable("subject") String subject,
                                 @PathVariable("id") Long id,
                                 @PathVariable String locale) {
        log.info("Register to course with ID ({})", id);
        registrationsService.registerByEmailAndCourseId(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                id
        );
        return "redirect:/" + locale + "/courses/" + subject + "/course_" + id + "?register";
    }

    @RequestMapping("/register/cancel")
    public String courseRegistrationCancel(@PathVariable("subject") String subject,
                                           @PathVariable("id") Long id,
                                           @PathVariable String locale) {
        log.info("Register to course with ID ({})", id);
        registrationsService.cancelRegistrationByEmailAndCourseId(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                id
        );
        return "redirect:/" + locale + "/courses/" + subject + "/course_" + id + "?canceled";
    }
}
