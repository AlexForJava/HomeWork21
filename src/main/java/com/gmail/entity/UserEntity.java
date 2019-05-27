package com.gmail.entity;

import com.gmail.listeners.UserEntityListener;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
@Accessors(chain = true)
@EntityListeners(value = UserEntityListener.class)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String userPassword;

    @Column
    private String email;

    @Column
    private Boolean enabled;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role userRole;
}
