package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import javax.validation.Constraint;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {

    public int userId;

    @Column
    public String name;

    @Column
    public String email;

    @Column
    public Date birthday;

    private User user;


    public UserDetails() {
    }

    public UserDetails(int userId, String name, String email, Date birthday) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id", unique = true, nullable = false)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @PrimaryKeyJoinColumn
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
