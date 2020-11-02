package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.CourseDTO;
import org.elective.dto.UserDTO;
import org.elective.entity.Course;
import org.elective.service.CoursesService;
import org.elective.service.RegistrationsService;
import org.elective.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/profile")
public class ProfileController {
    private static final String PROFILE_PAGE = "profile";
    private final UserService userService;

    @RequestMapping
    public String profilePage(@PathVariable String locale,
                                       Map<String, Object> model) {
        log.info("Profile page");
        Optional<UserDTO> course = userService.getByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName());
        if (!course.isPresent()) {
            log.error("Cant find profile of current user");
            return userService.errorPage(model, "Cant find profile of current user");
        }
        model.put("user", course.get());
        return PROFILE_PAGE;
    }

    @GetMapping("/modify")
    public String profileModifyPage(@PathVariable String locale,
                              Map<String, Object> model) {
        log.info("Profile modify page");
        // TODO
        return PROFILE_PAGE;
    }

    @PostMapping("/modify")
    public String profileModify(@PathVariable String locale,
                                    Map<String, Object> model) {
        log.info("Profile modify post controller");
        // TODO
        return PROFILE_PAGE;
    }

    @ModelAttribute
    public void preparePage(@PathVariable String locale,
                            HttpServletRequest request,
                            Map<String, Object> model) {
        userService.prepareAllStaticOnPage(model, locale);
        String uri = request.getRequestURI();
        model.put("uri", uri);
    }
}