package com.example.demo.service.Impl;

import com.example.demo.entity.User;
import com.example.demo.repositories.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
       return  userRepository.findAll();
    }

    @Override
    public void createUser(User user) {

    }
}
