package org.elective.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.service.LocalizedTextSupplier;
import org.elective.service.UsersService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/{locale:\\ben|ua\\b}/users")
public class UsersController {
    public static final String USERS_LIST = "users";
    public static final String PAGE_NAME = "users";

    private final UsersService usersService;
    private final LocalizedTextSupplier localizedTextSupplier;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping
    public String usersPage(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false) Optional<String> filterByCourse,
                            @RequestParam(required = false) Optional<String> deleted,
                            @RequestParam(required = false) Optional<String> added,
                            @PathVariable String locale,
                            Map<String, Object> model) {
        log.info("Users page (users table)");
        if (!filterByCourse.isPresent() || filterByCourse.get().isEmpty()) {
            model.put(USERS_LIST, usersService.getAllUsers(pageable));
        } else
            model.put(USERS_LIST, usersService.getUsersByCourseName(filterByCourse.get(), pageable));
        usersService.addMessageIfExistParam(model,
                localizedTextSupplier.getLocalizedText("users.msg.deleted", locale),
                deleted);
        usersService.addMessageIfExistParam(model,
                localizedTextSupplier.getLocalizedText("users.msg.added", locale),
                added);
        return PAGE_NAME;
    }

    @ModelAttribute
    public void preparePage(@PathVariable String locale,
                            HttpServletRequest request,
                            Map<String, Object> model) {
        usersService.prepareAllStaticOnPage(model, locale);
        String uri = request.getRequestURI();
        model.put("uri", uri);
    }
}
