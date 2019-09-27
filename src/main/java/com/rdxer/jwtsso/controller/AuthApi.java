package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuthApi {
    @Resource
    AccountRepository accountRepository;
    @GetMapping("/login")
    Object login(){
        return accountRepository.findAll();
//        return "1213";
    }
}
