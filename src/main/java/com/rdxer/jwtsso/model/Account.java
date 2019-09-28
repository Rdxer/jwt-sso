package com.rdxer.jwtsso.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;


/**
 * 账户表
 */
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @ManyToMany
    private List<Role> roles;

    @ManyToMany
    private List<Permission> permissions;

}
