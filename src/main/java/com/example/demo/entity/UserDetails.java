package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import javax.validation.Constraint;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "user_details")
@Table(name = "user_details", indexes = {@Index(name = "EMP_EMAIL_INDEX", columnList = "id,email")})
public class UserDetails implements Serializable {

    @Id
    @Column(name = "id")
    public int userId;

    @Column
    public String name;

    @Column(unique = true)
    public String email;

    @Column
    public Date birthday;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id")
    @MapsId
    private User user;


    public UserDetails() {
    }

    public UserDetails(int userId, String name, String email, Date birthday) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
