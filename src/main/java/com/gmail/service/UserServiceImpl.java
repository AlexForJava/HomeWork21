package com.gmail.service;

import com.gmail.entity.UserEntity;
import com.gmail.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        userRepository.save(buildEntity(user));
    }

    @Override
    public void update(User user) {
        userRepository.save(updateEntity(user));
    }

    @Override
    public User getById(Long id) {
        return buildUser(userRepository.findById(id).orElseThrow(NullPointerException::new));
    }

    @Override
    public User getByName(String name) {
        return buildUser(userRepository.getByUserName(name));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUser)
                .collect(Collectors.toList());
    }

    private UserEntity buildEntity(User user) {
        return new UserEntity()
                .setEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .setUserName(user.getUserName());
    }

    private User buildUser(UserEntity userEntity) {
        return new User()
                .setId(userEntity.getId())
                .setEmail(userEntity.getEmail())
                .setUserName(userEntity.getUserName())
                .setRole(userEntity.getUserRole());
    }

    private UserEntity updateEntity(User user) {
        return userRepository.findById(user.getId()).get()
                .setUserName(user.getUserName())
                .setEmail(user.getEmail())
                .setUserRole(user.getRole());
    }
}
