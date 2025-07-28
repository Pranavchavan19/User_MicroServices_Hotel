package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    // create user
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    //get single user according to user id
    User getUser(String userId);
}
