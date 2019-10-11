package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.repository.RoleRepository;
import com.rdxer.jwtsso.server.RoleServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class RoleServerImpl implements RoleServer {
    @Resource
    RoleRepository repository;
    @Override
    public @NotNull JpaRepository<Role, Long> getRepository() {
        return repository;
    }

    @Override
    public Role findByName(String name) {
        return repository.findRoleByName(name);
    }
}
