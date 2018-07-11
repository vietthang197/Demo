package com.example.demo.service;

import com.example.demo.entity.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDetailsService {
    public List<UserDetails> findAll();
}
