package org.elective.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocaleService {
    public static final String ENTRY_NAME = "locale";
    public void putLocale(Map<String, Object> model, String locale) {
        model.put(ENTRY_NAME, locale);
    }
}
