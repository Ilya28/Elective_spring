package org.elective.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elective.dto.UserDTO;
import org.elective.dto.UsersDTO;
import org.elective.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.elective.entity.User;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/{locale:en|ua}")
public class LoginController {
    private final UserService userService; // Autowired

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/login")
    public void loginFormController(UserDTO user){
        log.info("{}",userService.findByUserLogin(user));
        log.info("{}", user);
    }
}
