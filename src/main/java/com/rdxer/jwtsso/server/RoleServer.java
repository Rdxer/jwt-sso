package com.rdxer.jwtsso.server;


import com.rdxer.jwtsso.model.Role;
import com.rdxer.lib.core.base.CRUDExServiceInterface;

public interface RoleServer extends CRUDExServiceInterface<Role,Long> {
    Role findByName(String name);
}
