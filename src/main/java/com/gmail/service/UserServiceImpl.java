package com.gmail.service;

import com.gmail.entity.UserEntity;
import com.gmail.exceptions.UserNotFoundEception;
import com.gmail.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        userRepository.save(buildEntity(user));
    }

    @Override
    public void update(User user) throws UserNotFoundEception {
        userRepository.save(updateEntity(user));
    }

    @Override
    public User getById(Long id) throws UserNotFoundEception {
        return buildUser(userRepository.findById(id).orElseThrow(UserNotFoundEception::new));
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

    private UserEntity buildEntity(@NonNull User user) {
        return new UserEntity()
                .setEmail(user.getEmail())
                .setUserPassword(passwordEncoder.encode(user.getPassword()))
                .setUserName(user.getUserName());
    }

    private User buildUser(UserEntity userEntity) {
        return new User()
                .setId(userEntity.getId())
                .setEmail(userEntity.getEmail())
                .setUserName(userEntity.getUserName())
                .setRole(userEntity.getUserRole());
    }

    private UserEntity updateEntity(User user) throws UserNotFoundEception {
        return userRepository.findById(user.getId()).orElseThrow(UserNotFoundEception::new)
                .setUserName(user.getUserName())
                .setEmail(user.getEmail())
                .setUserRole(user.getRole());
    }
}
