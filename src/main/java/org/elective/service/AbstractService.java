package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.service.preparing.LocalizationPreparingService;
import org.elective.service.preparing.NavbarOnPageService;
import org.elective.service.preparing.RoleOnPageService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public abstract class AbstractService {
    private static final String MESSAGE_HOLDER = "message";

    private final LocalizationPreparingService localeService;
    private final NavbarOnPageService navbarService;
    private final RoleOnPageService roleService;

    /**
     * Places the elements needed to render the page into the container (model).
     * 1) Locale for locale switching mechanism.
     * 2) Subjects for navbar dropdown list
     * 3) Role for portioned content display (depending on the user's role).
     * 4) Put localization method. This method allows page to get localized message from property file
     * @param model the model into which the information will be placed.
     * @param locale current locale
     */
    public void prepareAllStaticOnPage(Map<String, Object> model, String locale) {
        localeService.putLocale(model, locale);                 // 1
        navbarService.putSubjects(model, locale);               // 2
        roleService.putRole(model);                             // 3
        localeService.putLocalizedTextSupplier(model, locale);  // 4
    }

    /**
     * Add message to model (to use on freemarker page) if exist parameter in request
     * @param model Model
     * @param message Message text
     * @param param Parameter
     */
    public void addMessageIfExistParam(Map<String, Object> model, String message, Optional<?> param) {
        param.ifPresent(o -> model.put(MESSAGE_HOLDER, message));
    }

    public String errorPage(Map<String, Object> model, String errorText) {
        log.debug("Error page: {}", errorText);
        model.put("error_message", errorText);
        return "my_error";
    }
}
