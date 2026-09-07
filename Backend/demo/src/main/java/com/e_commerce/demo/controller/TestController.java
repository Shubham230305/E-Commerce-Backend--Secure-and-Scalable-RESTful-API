package com.e_commerce.demo.controller;

import com.e_commerce.demo.entity.User;
import com.e_commerce.demo.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String test(){
        return "JWT Authenctication is working";
    }

    @GetMapping("/me")
    public String getCurrentUSer(Authentication authentication){
        UserPrincipal principal  = (UserPrincipal) authentication.getPrincipal();

        User user = principal.getUser();
        return "Currently logged-in user:"
                + user.getName()
                + " "
                + user.getEmail()
                + " "
                +user.getRole().getName();

    }
}
