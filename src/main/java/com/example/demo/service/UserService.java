package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    User findOneById(int id);

    User findOneByUsernameAndAge(String username, int age);
}
