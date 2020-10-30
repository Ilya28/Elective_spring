package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.service.LoginService;
import org.elective.service.UsersService;
import org.elective.service.preparing.PagePrepareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/*
TODO:
Добавить на странице аунтефикации вывод сообщений об ошибке, успешной регистрации и выходе из аккаунта
(... model, Str error, Str logout, Str successful)
    if error != null)
        model.add ...
    ...

*/

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/{locale:\\ben|ua\\b}")
public class LoginController {
    private final UsersService userService;
    private final LoginService loginService;

    private final PagePrepareService pagePrepareService;


    @GetMapping(value = "/login")
    public ModelAndView loginPageController(Map<String, Object> model,
                                            @PathVariable String locale){
        log.info("Login/register page");
        pagePrepareService.prepareAllStaticOnPage(model, locale);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/register")
    public ResponseEntity<String> registerFormPostController(@RequestBody UserDTO auth,
                                                             @PathVariable String locale){
        log.info("New user");
        boolean result = loginService.saveNewUser(auth, locale);
        log.info(result ? "Register: new user added": "Register: user with this email already exist");
        log.info("> {}", auth);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
