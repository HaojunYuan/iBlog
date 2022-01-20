package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;

import java.util.List;

public interface UserService {

    ListableUserQuery findByQuery(ListableUserQuery query);

    long countByQuery(ListableUserQuery query);

    List<User> findAllUser();

    User findOneById(int id);

    User findOneByUsername(String username);

    boolean deleteUserById(int id);

    boolean createNewUser(User user);

    boolean editUser(User user);
}
