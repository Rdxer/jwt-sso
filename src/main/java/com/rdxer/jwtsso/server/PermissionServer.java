package com.rdxer.jwtsso.server;


import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.lib.core.base.CRUDExServiceInterface;

public interface PermissionServer extends CRUDExServiceInterface<Permission,Long> {
    Permission findByName(String name);
}
