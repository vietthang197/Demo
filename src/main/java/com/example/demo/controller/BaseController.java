package com.example.demo.controller;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.User;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.controller"})
@RequestMapping("/")
public class BaseController {

    @Autowired
    private CustomUserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping
    public String getDefault() {
        return "MusicServer, development by : Lê Việt Thắng, OS: Ubuntu 64bit";
    }

    @PostMapping("/register")
    public String registerUserAccount(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.createUserAccount(userRegisterDTO);
    }

    @PostMapping("/validate-form/check-exists-username")
    @CrossOrigin
    public boolean checkExistsUsername(@RequestBody Map<String, String> map) {
        return userService.checkExistsUsername(map.get("username"));
    }

    @PostMapping("/validate-form/check-exists-email")
    @CrossOrigin
    public boolean checkExistsEmail(@RequestBody Map<String, String> map) {
        return userDetailsService.checkExistsEmail(map.get("email"));
    }
}
