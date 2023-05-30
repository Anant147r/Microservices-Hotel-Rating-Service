package com.lcwd.user.service.UserService.services;

import com.lcwd.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

//    user operations

//    create
    User saveUser(User user);

//    get all users
    List<User> getAllUser();

//    get single user of userId
    User getUser(String userId);

//    TODO: delete
//    TODO: update
}
