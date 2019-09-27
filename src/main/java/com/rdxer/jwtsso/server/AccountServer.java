package com.rdxer.jwtsso.server;


import com.rdxer.jwtsso.model.Account;

import java.util.List;

public interface AccountServer {
    List<Account> findAll();
}
