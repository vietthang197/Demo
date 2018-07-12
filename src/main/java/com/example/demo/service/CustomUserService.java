package com.example.demo.service;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomUserService {
     List<User> findAllUser();
     void createUser(User user);
     User findUserByEmail(String email);
     User findUserByUsername(String username);
     String createUserAccount(UserRegisterDTO userRegisterDTO);
     boolean checkExistsUsername(String username);
}
