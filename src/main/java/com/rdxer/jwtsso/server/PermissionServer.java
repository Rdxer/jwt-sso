package com.rdxer.jwtsso.server;


import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Permission;

import java.util.List;

public interface PermissionServer {
    List<Permission> findAll();
}
