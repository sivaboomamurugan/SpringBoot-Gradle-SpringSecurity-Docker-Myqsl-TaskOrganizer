package com.siva.taskorganizer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author by Siva Murugan
 */
@Entity(name = "user")
public class User {

    public User() {

    }
    public User(User user) {
        this.username = user.username;
        this.password = user.password;
        this.userid = user.userid;
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

    private String username;

    private String password;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;


}
