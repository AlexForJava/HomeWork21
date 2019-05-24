package com.gmail.service;

import java.util.List;

public interface UserService {
    void create(User user);
    void update(User user);
    User getById(Long id);
    User getByName(String name);
    void deleteById(Long id);
    List<User> getAll();
}
