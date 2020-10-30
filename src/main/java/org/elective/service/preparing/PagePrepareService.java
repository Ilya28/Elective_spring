package org.elective.service.preparing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagePrepareService {
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

    public void addMessageIfExistParam(Map<String, Object> model, String message, Optional<?> param) {
        param.ifPresent(o -> model.put(MESSAGE_HOLDER, message));
    }
}
