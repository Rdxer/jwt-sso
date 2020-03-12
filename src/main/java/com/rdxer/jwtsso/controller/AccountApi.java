package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/api/account")
public class AccountApi {

    @Resource
    AccountServer accountServer;

    @GetMapping("/")
    java.util.List<Account> index() {
//        Account show = accountServer.show((long) 1);

        return accountServer.getAll();
    }

    @GetMapping("/{id}")
    Account index(@PathVariable Long id) {

        return accountServer.show(id);
    }

}
