package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void deleteUser(int id);

    void editUser(User user);


    void addUser(User user);

    User getUserById(int id);

    User getUserByUserName(String username);
}
