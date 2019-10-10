package com.rdxer.jwtsso.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


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

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions;

}
