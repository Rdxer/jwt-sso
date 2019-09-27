package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.server.AccountServer;
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
    Object index(){
        return accountServer.findAll();
    }
}
