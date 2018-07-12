package com.example.demo.repositories.repository;

import com.example.demo.entity.UserDetails;
import com.example.demo.repositories.custom.CustomUserDetailsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>, CustomUserDetailsRepository {

}
