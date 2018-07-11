package com.example.demo.service.Impl;

import com.example.demo.repositories.repository.UserDetailsRepository;
import com.example.demo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<com.example.demo.entity.UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }
}
