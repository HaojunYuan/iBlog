package com.example.demo.controllers;
/*
 * This file is part of the kernelstudio package.
 *
 * (c) 2014-2021 zlin <admin@kernelstudio.com>
 *
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 */

import com.example.demo.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<User>> users() {
        List<User> users = userService.findAllUser();
        return new ApiResponse<>(true, users);
    }

    @GetMapping("/users/{id}")
    public ApiResponse<User> users(@PathVariable int id) {
        User user = userService.findOneById(id);
        return new ApiResponse<>(true, user);
    }


}
