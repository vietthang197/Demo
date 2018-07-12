package com.example.demo.service.Impl;

import com.example.demo.entity.User;
import com.example.demo.repositories.repository.UserDetailsRepository;
import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<com.example.demo.entity.UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }

    @Override
    public boolean checkExistsEmail(String email) {
        return userDetailsRepository.checkExistsEmail(email);
    }
}
