package com.example.demo.repositories.repository;

import com.example.demo.entity.Role;
import com.example.demo.repositories.custom.CustomRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, CustomRoleRepository {
}
