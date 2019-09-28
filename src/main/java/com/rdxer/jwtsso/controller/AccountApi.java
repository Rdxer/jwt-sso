package com.rdxer.jwtsso.controller;

import com.rdxer.lib.exception.exceptions.InvalidRequestException;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/api/account")
public class AccountApi {

    @Resource
    AccountServer accountServer;

    @GetMapping("/")
    Object index() {
        System.out.println("______________");
        if (true){
            throw new InvalidRequestException(new BindException(this,"AA"));
        }
        return accountServer.findAll();
    }
}
