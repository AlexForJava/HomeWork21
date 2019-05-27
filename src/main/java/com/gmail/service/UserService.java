package com.gmail.service;

import com.gmail.exceptions.UserNotFoundEception;

import java.util.List;

public interface UserService {
    void create(User user);

    void update(User user) throws UserNotFoundEception;

    User getById(Long id) throws UserNotFoundEception;

    User getByName(String name);

    void deleteById(Long id);

    List<User> getAll();
}
