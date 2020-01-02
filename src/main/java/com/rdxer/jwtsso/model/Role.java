package com.rdxer.jwtsso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;
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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<Account> accounts;
    @Tolerate
    public Role() {
    }


    public enum NAME{
        SUPER_ADMIN,
        ADMIN,
        USER
    }
}

