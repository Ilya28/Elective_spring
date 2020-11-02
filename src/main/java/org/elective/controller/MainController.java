package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {
    public static final String DEFAULT_LOCALE = "en";

    @RequestMapping("/")
    public String mainPage(){
        log.info("Main page - missing locale, redirect to " + DEFAULT_LOCALE + " version");
        return ("redirect:/" + DEFAULT_LOCALE + "/home");
    }
}
