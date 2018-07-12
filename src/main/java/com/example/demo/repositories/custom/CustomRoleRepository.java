package com.example.demo.repositories.custom;

import com.example.demo.entity.Role;

import java.util.Set;

public interface CustomRoleRepository {
    Set<Role> findRoleByName(String name);
}
