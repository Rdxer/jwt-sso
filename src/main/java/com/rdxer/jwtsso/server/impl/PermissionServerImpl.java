package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.repository.PermissionRepository;
import com.rdxer.jwtsso.server.PermissionServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class PermissionServerImpl implements PermissionServer {
    @Resource
    PermissionRepository repository;
    @Override
    public Permission findByName(String name) {
        return repository.findPermissionByName(name);
    }

    @Override
    public @NotNull JpaRepository<Permission, Long> getRepository() {
        return repository;
    }
}
