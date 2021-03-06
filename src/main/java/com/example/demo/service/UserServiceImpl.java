package com.example.demo.service;
/*
 * This file is part of the kernelstudio package.
 *
 * (c) 2014-2021 zlin <admin@kernelstudio.com>
 *
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 */

import com.example.demo.entity.User;
import com.example.demo.query.ListableUserQuery;
import com.example.demo.respsitory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ListableUserQuery findByQuery(ListableUserQuery query) {
        long total = countByQuery(query);
        if (total>0){
            query.total(total).items(userRepository.findByQuery(query));
        }
        return query;
    }

    @Override
    public long countByQuery(ListableUserQuery query) {
        return userRepository.countByQuery(query);
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = userRepository.findAllUser();
        if (null !=users){
            users.forEach(user->{
                user.setPassword(null);
            });
        }
        return users;
    }

    @Override
    public User findOneById(int id) {
        User user = userRepository.findOneById(id);
        if (null == user){
            throw new RuntimeException("User not exists.");
        }
        return user;
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public boolean deleteUserById(int id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public boolean createNewUser(User user) {
        check(user);
        return userRepository.createNewUser(user);
    }

    @Override
    public boolean editUser(User user) {
        check(user);
        return userRepository.editUser(user);
    }

    private void check(User user) {
        boolean has = userRepository.has(user);
        if (has){
            throw new IllegalArgumentException("User exists.");
        }
    }
}
