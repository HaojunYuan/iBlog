package com.example.demo.controllers;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        User user = new User("user","111","111");
        model.addAttribute("user",user);
        return "index";
    }
}
