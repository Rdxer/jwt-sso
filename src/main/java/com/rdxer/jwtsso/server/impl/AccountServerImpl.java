package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServerImpl implements AccountServer {
    @Resource
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

}
