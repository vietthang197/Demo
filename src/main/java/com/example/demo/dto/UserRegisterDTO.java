package com.example.demo.dto;

import java.io.Serializable;
import java.sql.Date;

public class UserRegisterDTO implements Serializable {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String confirmEmail;
    private String name;
    private Date birthday;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String username, String password, String confirmPassword, String email, String confirmEmail, String name, Date birthday) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.name = name;
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
