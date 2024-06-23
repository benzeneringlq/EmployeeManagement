package com.itranswarp.learnjava.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/welcome")
    public String showWelcomePage() {
        System.out.println("Hello!!!!!");
        return "Hello!!!!!";
    }

}