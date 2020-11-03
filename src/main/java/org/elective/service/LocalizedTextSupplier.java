package org.elective.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class LocalizedTextSupplier {
    private final MessageSource messageSource;

    /**
     * Get localized text from messageSource (property file)
     * @param messageKey key in property file
     * @param locale Locale as String ('en' or 'ua')
     * @return Localized text (messge)
     */
    public String getLocalizedText(String messageKey, String locale) {
        return messageSource.getMessage(messageKey, new Object[0], new Locale(locale));
    }
}
