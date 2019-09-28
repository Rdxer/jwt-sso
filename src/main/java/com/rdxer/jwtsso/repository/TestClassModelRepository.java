package com.rdxer.jwtsso.repository;

import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.model.TestClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestClassModelRepository extends JpaRepository<TestClassModel, Long> {

}
