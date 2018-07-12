package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomUserDetailsService {
    List<UserDetails> findAll();
    boolean checkExistsEmail(String email);
}
