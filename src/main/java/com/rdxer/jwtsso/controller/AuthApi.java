package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthApi {
    @Resource
    AccountServer accountServer;

    @PostMapping("/signup")
    public Account signup(Account account){
        Account register = accountServer.register(account);
        return register;
    }


}
