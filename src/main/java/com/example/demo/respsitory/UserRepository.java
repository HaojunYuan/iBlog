package com.example.demo.respsitory;

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> findByQuery(ListableUserQuery query);

    long countByQuery(ListableUserQuery query);

    List<User> findAllUser();

    User findOneById(int id);

    User findOneByUsername(String username);

    boolean deleteUserById(int id);

    boolean has(User user);

    boolean createNewUser(User user);

    boolean editUser(User user);
}
