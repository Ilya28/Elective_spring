package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.service.LoginService;
import org.elective.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/{locale:\\ben|ua\\b}")
public class LoginController {
    private final UserService userService;
    private final LoginService loginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/register")
    public ResponseEntity<String> registerFormController(@RequestBody UserDTO auth){
        log.info("{new user}");
        boolean result = loginService.saveUser(auth);
        log.info(result ? "{new user added}": "{user with this email already exist}");
        log.info("{}", auth);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginFormController(@RequestBody UserDTO auth){

        log.info("{}",userService.findByUserLogin(auth));
        log.info("{}", auth);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
