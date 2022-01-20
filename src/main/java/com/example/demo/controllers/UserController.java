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
import com.example.demo.query.ListableUserQuery;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * /api/v1/users?page=2&s=admin&offset=15
     * @return
     */
    @GetMapping("/users")
    public ApiResponse<ListableUserQuery> users(ListableUserQuery query) {
        userService.findByQuery(query);
        return new ApiResponse<>(true,query);
    }

    @GetMapping("/users/{id}")
    public ApiResponse<User> users(@PathVariable int id) {
        User user = userService.findOneById(id);
        return new ApiResponse<>(true, user);
    }

    @GetMapping("/users/{id}/delete")
    public ApiResponse<String> deleteUserById(@PathVariable int id){
        boolean deleted= userService.deleteUserById(id);
        return new ApiResponse<>(deleted,deleted ? "Success" : "Fail");
    }

    @PostMapping("/users/create")
    public ApiResponse<String> createNewUser(User user){
        boolean created= userService.createNewUser(user);
        return new ApiResponse<>(created,created ? "Success" : "Fail");
    }

    @RequestMapping("/users/{id}/edit")
    public ApiResponse<String> editUser(User user){
        boolean edited= userService.editUser(user);
        return new ApiResponse<>(edited,edited ? "Success" : "Fail");
    }




}
