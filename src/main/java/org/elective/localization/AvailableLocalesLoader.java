package org.elective.localization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

// TODO: Thread safety

@Slf4j
@Service
public class AvailableLocalesLoader {
    private static final String PROP_FILE = "application.properties";
    public static final String LOCALES_PARAMETER = "locales";

    private List<String> cached = null;

    public List<String> getAvailableLocales() {
        if (cached == null) {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROP_FILE);
            try {
                properties.load(inputStream);
            } catch (IOException exception) {
                log.error("Cant read property file", exception);
                throw new IllegalStateException("Cant read property file");
            }
            cached = Arrays.asList(
                    properties.getProperty(LOCALES_PARAMETER).split("[\\s,]+")
            );
            log.info("Available locales: {}", cached);
        }
        return cached;
    }

    public String getLocalesAsPattern() {
        List<String> locales = getAvailableLocales();
        return "\\b" + String.join("|", locales) + "\\b";
    }
}
