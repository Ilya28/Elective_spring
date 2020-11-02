package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.service.LocalizedTextSupplier;
import org.elective.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class LoginController {
    private static final String SESSION_LOCALE_HOLDER = "locale";
    private final LoginService loginService;
    private final LocalizedTextSupplier localizedTextSupplier;

    @GetMapping(value = "login")
    public ModelAndView loginPageController(HttpServletRequest request,
                                            @ModelAttribute String lang,
                                            @ModelAttribute Optional<String> logout){
        log.info("Login/register page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        HttpSession session = request.getSession();
        Optional<String> sessionLocale = Optional.ofNullable((String) session.getAttribute(SESSION_LOCALE_HOLDER));
        loginService.addMessageIfExistParam(
                modelAndView.getModel(),
                localizedTextSupplier.getLocalizedText("login.logout", sessionLocale.orElse("en")),
                logout);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "register")
    public ResponseEntity<String> registerFormPostController(HttpServletRequest request,
                                                             @RequestBody UserDTO auth){
        log.info("New user");
        HttpSession session = request.getSession();
        Optional<String> sessionLocale = Optional.ofNullable((String) session.getAttribute(SESSION_LOCALE_HOLDER));
        boolean result = loginService.saveNewUser(auth, sessionLocale.orElse("en"));
        log.info(result ? "Register: new user added": "Register: user with this email already exist");
        log.info("> {}", auth);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @ModelAttribute
    public void preparePage(HttpServletRequest request,
                            @ModelAttribute Optional<String> lang,
                            Map<String, Object> model) {
        HttpSession session = request.getSession();
        Optional<String> sessionLocale = Optional.ofNullable((String) session.getAttribute(SESSION_LOCALE_HOLDER));
        if (!sessionLocale.isPresent() || sessionLocale.get().isEmpty()) {
            session.setAttribute(SESSION_LOCALE_HOLDER, lang.orElse("en"));
            sessionLocale = Optional.of(lang.orElse("en"));
            log.info("Locale changed to {}", sessionLocale.get());
        }
        loginService.prepareAllStaticOnPage(model, sessionLocale.get());
        String uri = request.getRequestURI();
        model.put("uri", uri);
    }
}
