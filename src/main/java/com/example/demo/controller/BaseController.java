package com.example.demo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.controller"})
@RequestMapping("/user")
public class BaseController {
    @GetMapping
    public String defaults(){
        return "Hello casc ban";
    }
}
