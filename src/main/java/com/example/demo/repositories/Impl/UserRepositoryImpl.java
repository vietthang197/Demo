package com.example.demo.repositories.Impl;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.repositories.custom.CustomUserDetailsRepository;
import com.example.demo.repositories.custom.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserRepositoryImpl  implements CustomUserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findUserByEmail(String email) {
        List<User> users = entityManager
                .createNativeQuery("SELECT u.* FROM public.user u INNER JOIN public.user_details ud ON u.id = ud.id WHERE ud.email= ?1", User.class)
                .setParameter(1, email)
                .getResultList();
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        List<User> users =  entityManager
                .createNativeQuery("SELECT u.* FROM public.user u WHERE u.username = ?1", User.class)
                .setParameter(1, username)
                .getResultList();
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    @Override
    public boolean checkExistsUsername(String username) {
        BigInteger count = (BigInteger) entityManager.createNativeQuery("SELECT COUNT(u.id) FROM public.user u WHERE u.username = ?1")
                .setParameter(1, username)
                .getSingleResult();

        return count.longValue() == 0 ? false: true;
    }
}
