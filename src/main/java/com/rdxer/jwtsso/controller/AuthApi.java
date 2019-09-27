package com.rdxer.jwtsso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApi {
    @GetMapping("/login")
    Object login(){
        return "1213";
    }
}
