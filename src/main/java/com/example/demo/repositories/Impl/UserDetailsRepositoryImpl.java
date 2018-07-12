package com.example.demo.repositories.Impl;

import com.example.demo.entity.User;
import com.example.demo.repositories.custom.CustomUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
public class UserDetailsRepositoryImpl implements CustomUserDetailsRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean checkExistsEmail(String email) {
        BigInteger count = (BigInteger) entityManager
                .createNativeQuery("SELECT COUNT(ud.id) FROM public.user_details ud WHERE ud.email = ?1")
                .setParameter(1, email)
                .getSingleResult();
        return count.longValue() == 0 ? false: true;
    }
}
