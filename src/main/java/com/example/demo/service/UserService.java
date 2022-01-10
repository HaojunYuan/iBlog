package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;
import com.example.demo.query.UserQuery;

import java.util.List;

public interface UserService {

    ListableUserQuery findByQuery(ListableUserQuery query);

    long countByQuery(ListableUserQuery query);

    List<User> findAllUser();

    User findOneById(int id);

    User findOneByUsernameAndAge(String username, int age);
}
