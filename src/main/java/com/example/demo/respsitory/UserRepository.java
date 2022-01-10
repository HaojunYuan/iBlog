package com.example.demo.respsitory;

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;
import com.example.demo.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> findByQuery(ListableUserQuery query);

    long countByQuery(ListableUserQuery query);

    List<User> findAllUser();

    User findOneById(int id);

    User findOneByUsernameAndAge(String username, int age);
}
