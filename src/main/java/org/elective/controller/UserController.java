package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.service.RegistrationsService;
import org.elective.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/users/user_{id:\\d+}")
public class UserController {
    private static final String USER_OBJECT = "user";
    private static final String REGISTRATIONS_LIST = "registrations";
    private static  final  String COURSE_STATUS_OBJECT = "course_status";
    private static final String PAGE_NAME = "user";

    private final UserService userService;
    private final RegistrationsService registrationsService;

    @RequestMapping
    public String userPage(@PageableDefault Pageable pageable,
                           @PathVariable Long id,
                           @PathVariable String locale,
                           Map<String, Object> model) {
        log.info("User page (id = {})", id);
        model.put(USER_OBJECT, userService.getUserById(id).orElse(null));
        model.put(REGISTRATIONS_LIST, registrationsService.getAllRegistrationsForUser(id, locale, pageable));
        return PAGE_NAME;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/delete")
    public String userDelete(@PathVariable Long id,
                             @PathVariable String locale,
                             Map<String, Object> model) {
        log.info("User delete (id = {})", id);

        return "redirect:/" + locale + "/users";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/modify")
    public String userModifyPage(@PathVariable Long id,
                                 @PathVariable String locale,
                                 Map<String, Object> model) {
        log.info("User modify (id = {})", id);
        model.put(USER_OBJECT, userService.getUserById(id).orElse(null));
        return "user_modify";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/modify")
    public String userModify(@ModelAttribute UserDTO user,
                             @PathVariable Long id,
                             @PathVariable String locale,
                             Map<String, Object> model) {
        log.info("User delete (id = {})", id);
        user.setId(id);
        userService.modifyUser(user);
        return "redirect:/" + locale + "/users/user_"+id;
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
