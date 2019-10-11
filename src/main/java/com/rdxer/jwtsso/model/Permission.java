package com.rdxer.jwtsso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色表
 */
@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"accounts"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    private Set<Account> accounts;
    @Tolerate
    public Permission() {
    }

}
