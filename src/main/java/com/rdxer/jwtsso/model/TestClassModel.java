package com.rdxer.jwtsso.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SQLDelete(sql = "update test_class_model set delete_at = now() where id = ?")
@Where(clause = "delete_at is null")
@Table(name = "test_class_model")
@Getter
@Setter
public class TestClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // 如果不为空  则已经删除
    LocalDateTime deleteAt;


}
