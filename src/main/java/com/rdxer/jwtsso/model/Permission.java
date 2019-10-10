package com.rdxer.jwtsso.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 权限表
 */
@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    private Set<Account> accounts;

}
