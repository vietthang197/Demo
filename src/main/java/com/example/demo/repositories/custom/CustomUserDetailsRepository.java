package com.example.demo.repositories.custom;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


public interface CustomUserDetailsRepository {
    boolean checkExistsEmail(String email);
}
