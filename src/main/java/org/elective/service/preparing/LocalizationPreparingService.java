package org.elective.service.preparing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.localization.LocalizedTextResolverMethod;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocalizationPreparingService {
    public static final String ENTRY_NAME = "locale";

    private final MessageSource messageSource;

    /**
     * Put locale object (String) to model
     * @param model freemarker Model
     * @param locale Locale as String ('en' or 'ua')
     */
    public void putLocale(Map<String, Object> model, String locale) {
        model.put(ENTRY_NAME, locale);
    }

    /**
     * Put freemarker method, which is needed to get access to locale property
     * @param model freemarker model
     * @param locale Locale
     */
    public void putLocalizedTextSupplier(Map<String, Object> model, String locale) {
        model.put("msg", new LocalizedTextResolverMethod(messageSource, new Locale(locale)));
    }
}
