package com.example.com.cognizant.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String profileimg;

    public User() {}
    public User(int id, String username, String profileimg) {
        this.id = id;
        this.username = username;
        this.profileimg = profileimg;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getProfileimg() {
        return profileimg;
    }
    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }
}
