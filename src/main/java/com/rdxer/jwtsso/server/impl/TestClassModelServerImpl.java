package com.rdxer.jwtsso.server.impl;

import com.rdxer.jwtsso.model.TestClassModel;
import com.rdxer.jwtsso.repository.TestClassModelRepository;
import com.rdxer.jwtsso.server.TestClassModelServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class TestClassModelServerImpl implements TestClassModelServer {
    @Resource
    TestClassModelRepository repository;
    @Override
    public @NotNull JpaRepository<TestClassModel, Long> getRepository() {
        return repository;
    }
}
