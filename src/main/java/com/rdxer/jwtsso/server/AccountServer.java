package com.rdxer.jwtsso.server;


import com.rdxer.jwtsso.model.Account;
import com.rdxer.lib.core.base.CRUDExServiceInterface;

import java.util.List;
import java.util.Optional;

public interface AccountServer extends CRUDExServiceInterface<Account,Long> {
    Account findByName(String name);
}
