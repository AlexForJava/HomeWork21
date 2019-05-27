package com.gmail.service;

import com.gmail.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class User {
    private Long id;
    private String email;
    private String userName;
    private String password;
    private Role role = Role.USER;
}
