package com.rdxer.jwtsso.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;


/**
 * 账户表
 */
@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"roles"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToMany
    private Set<Role> roles;
    @ManyToMany
    private Set<Permission> permissions;

    @Tolerate
    public Account() {

    }
}
