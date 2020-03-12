package com.rdxer.jwtsso.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rdxer.jwtsso.model.Account;
import com.rdxer.jwtsso.model.Permission;
import com.rdxer.jwtsso.model.Role;
import com.rdxer.jwtsso.repository.AccountRepository;
import com.rdxer.jwtsso.server.AccountServer;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthApi {
    @Resource
    AccountServer accountServer;

    @PostMapping("/signup")
    public Account signup(AccountPara account){
        Account a = new Account();

        BeanUtils.copyProperties(account,a);

        Account register = accountServer.register(a);

        return register;
    }
}

@Data
class AccountPara {

    private String username;
    private String email;
    private String phone;

    private String password;

}
