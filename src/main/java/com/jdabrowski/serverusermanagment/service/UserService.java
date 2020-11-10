package com.jdabrowski.serverusermanagment.service;

import com.jdabrowski.serverusermanagment.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<User> findUsers(List<Long> idList);

    List<User> findAllUsers();

    User findUserByIdAndUsername(User user);
}
