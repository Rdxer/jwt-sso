package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class AccountServerImpl implements AccountServer {

    @Resource
    AccountRepository repository;

    @Override
    public @NotNull AccountRepository getRepository() {
        return repository;
    }

    @Override
    public Account findByName(String name){
        return getRepository().findAccountByUsername(name);
    }

}
