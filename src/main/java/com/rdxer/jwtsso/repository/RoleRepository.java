package com.rdxer.jwtsso.repository;

import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
