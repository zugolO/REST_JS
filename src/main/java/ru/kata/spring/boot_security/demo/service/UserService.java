package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean create(User user);

    void delete(Long id);

    void update(User user);

    User findByUsername(String username);
}
