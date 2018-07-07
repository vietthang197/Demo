package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class UserGrantedAuthority implements GrantedAuthority, Serializable {
    public String authority;

    public UserGrantedAuthority(String authority) {
        this.authority = authority;
    }

    public UserGrantedAuthority() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
