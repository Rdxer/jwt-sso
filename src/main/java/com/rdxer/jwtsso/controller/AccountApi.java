package com.rdxer.jwtsso.controller;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.server.TestClassModelServer;
import com.rdxer.lib.core.util.CRUDUtlis;
import com.rdxer.lib.exception.exceptions.InvalidRequestException;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/api/account")
public class AccountApi {

    @Resource
    AccountServer accountServer;

    @GetMapping("/")
    Object index() {
//        Account show = accountServer.show((long) 1);

        return accountServer.getAll();
    }

    @GetMapping("/{id}")
    Object index(@PathVariable Long id) {

        return accountServer.show(id);
    }

}
