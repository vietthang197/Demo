package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.service.UserDetailsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.controller"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping
    public String defaults(){
        return "Hello casc ban";
    }

    @GetMapping("/list-user")
    public List<User> listUser(){
        return userService.findAllUser();
    }

    @GetMapping("/details-user")
    public List<UserDetails> listUserDetails() {
        return userDetailsService.findAll();
    }
}
