package com.rdxer.jwtsso.repository;

import com.rdxer.jwtsso.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
