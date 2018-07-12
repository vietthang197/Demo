package com.example.demo.service.Impl;

import com.example.demo.constants.Constant;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.model.Validator;
import com.example.demo.repositories.repository.RoleRepository;
import com.example.demo.repositories.repository.UserDetailsRepository;
import com.example.demo.repositories.repository.UserRepository;
import com.example.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements CustomUserService, Constant {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAllUser() {
       return  userRepository.findAll();
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public String createUserAccount(UserRegisterDTO userRegisterDTO) {
        boolean accept = Validator.validateUserRegisterDTO(userRegisterDTO);
        boolean availableEmail = userRepository.findUserByEmail(userRegisterDTO.getEmail()) == null? true : false;
        boolean availableUsername = userRepository.findUserByUsername(userRegisterDTO.getUsername()) == null? true : false;

        boolean create = accept && availableEmail && availableUsername;
        if(create) {
               userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
               userRegisterDTO.setConfirmPassword(passwordEncoder.encode(userRegisterDTO.getConfirmPassword()));

               User user = new User();

               user.setUsername(userRegisterDTO.getUsername());
               user.setPassword(userRegisterDTO.getPassword());
               user.setEnable(true);

               Set<Role> roles = roleRepository.findRoleByName("USER");
               user.setRoles(roles);

               user = userRepository.save(user);

               UserDetails userDetails = new UserDetails();
               userDetails.setUser(user);
               userDetails.setEmail(userRegisterDTO.getEmail());
               userDetails.setName(userRegisterDTO.getName());
               userDetails.setBirthday(userRegisterDTO.getBirthday());

               userDetailsRepository.save(userDetails);

               return STATUS_SUCCESS;
        }
        return STATUS_FAILED;
    }

    @Override
    public boolean checkExistsUsername(String username) {
        return userRepository.checkExistsUsername(username);
    }

}
