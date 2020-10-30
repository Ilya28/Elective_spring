package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.service.preparing.PagePrepareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {
    public static final String MAIN_PAGE_NAME = "home";
    public static final String DEFAULT_LOCALE = "en";

    private final PagePrepareService pagePrepareService;

    @RequestMapping("/")
    public String mainPage(Map<String, Object> model){
        log.info("Main page - missing locale, redirect to " + DEFAULT_LOCALE + " version");
        return ("redirect:/" + DEFAULT_LOCALE + "/home");
    }

    @RequestMapping("/{locale:\\ben|ua\\b}/home")
    public String mainPageWithLocale(Map<String, Object> model,
                                     @PathVariable String locale){
        log.info("Main page");
        pagePrepareService.prepareAllStaticOnPage(model, locale);
        return MAIN_PAGE_NAME;
    }
}
