package com.example.demo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class UserProfileDTO implements Serializable {
    private String authorization;
    private String username;
    private List<GrantedAuthority> listRole;

    public UserProfileDTO() {

    }

    public UserProfileDTO(String authorization, String username, List<GrantedAuthority>listRole) {
        this.authorization = authorization;
        this.username = username;
        this.listRole = listRole;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GrantedAuthority> getListRole() {
        return listRole;
    }

    public void setListRole(List<GrantedAuthority> listRole) {
        this.listRole = listRole;
    }
}
