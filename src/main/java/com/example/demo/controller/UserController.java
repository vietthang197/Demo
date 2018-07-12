package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.controller"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomUserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping
    public String defaults(){
        return "Hello cac ban";
    }

    @GetMapping("/list-user")
    public List<User> listUser(){
        return userService.findAllUser();
    }

    @GetMapping("/details-user")
    public List<UserDetails> listUserDetails() {
        return userDetailsService.findAll();
    }

    @GetMapping("/get-user/{username}")
    public User findUser(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}
