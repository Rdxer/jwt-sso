package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class AccountServerImpl implements AccountServer {

    @Resource
    AccountRepository repository;
    @Resource
    PasswordEncoder passwordEncoder;


    @Override
    public @NotNull AccountRepository getRepository() {
        return repository;
    }

    @Override
    public Account findByName(String name){
        return getRepository().findAccountByUsername(name);
    }

    @Override
    public Account register(Account model) {
        String encodepwd = passwordEncoder.encode(model.getPassword());
        model.setPassword(encodepwd);
        return store(model);
    }

    @Override
    public Account update_exec(Account model) {
//        model.setDisabled(true);
        var save = getRepository().save(model);
        return save;
    }
}
