package org.elective.controller;

import org.elective.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    UserRepo userRepo;

    @RequestMapping("/")
    public String mainPage(Map<String, Object> model){
        return "index";
    }

    @GetMapping("/api")
    public String apiPage(){
        return "index";
    }

    @GetMapping("/form")
    public String regForm(){
        return "rest_reg_form";
    }
}
