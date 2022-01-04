package com.example.demo.respsitory;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> findAllUser();

    User findOneById(int id);

    User findOneByUsernameAndAge(String username, int age);
}
