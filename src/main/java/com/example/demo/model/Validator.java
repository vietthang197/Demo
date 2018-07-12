package com.example.demo.model;

import com.example.demo.dto.UserRegisterDTO;

import java.util.regex.Pattern;

public class Validator{

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{1,15}$";
    private static final String EMAIL_PATTERN = "^[\\w-]+@[\\w]+[.]+[\\w]{2,6}$";

    public static   boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    public static   boolean validateUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        return pattern.matcher(username).matches();
    }
    public static   boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

    public static boolean validateUserRegisterDTO(UserRegisterDTO userRegisterDTO) {
        return  validateUsername(userRegisterDTO.getUsername())
                && validatePassword(userRegisterDTO.getPassword())
                && validateEmail(userRegisterDTO.getEmail())
                && userRegisterDTO.getEmail().equals(userRegisterDTO.getConfirmEmail())
                && userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
    }
}
