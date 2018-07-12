package com.example.demo.repositories.custom;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;


public interface CustomUserRepository {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    boolean checkExistsUsername(String username);
}
