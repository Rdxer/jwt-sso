package com.rdxer.jwtsso.repository;

import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
