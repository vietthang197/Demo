package com.example.demo.repositories.repository;

import com.example.demo.entity.User;
import com.example.demo.repositories.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , CustomUserRepository {

}
