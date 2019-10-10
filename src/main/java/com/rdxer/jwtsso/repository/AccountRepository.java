package com.rdxer.jwtsso.repository;

import com.rdxer.jwtsso.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lxf
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * 根据用户名查找 用户
     * @param username 用户名称
     * @return 用户
     */
    Account findAccountByUsername(String username);
}
