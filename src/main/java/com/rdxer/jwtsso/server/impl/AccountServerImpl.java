package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Service
public class AccountServerImpl implements AccountServer {

    @Resource
    AccountRepository repository;

    @Override
    public Long modelGetId(Account model) {
        return model.getId();
    }

    @Override
    public void modelSetId(Long aLong, Account model) {
        model.setId(aLong);
    }

    @Override
    public @NotNull JpaRepository<Account, Long> getRepository() {
        return repository;
    }



}
