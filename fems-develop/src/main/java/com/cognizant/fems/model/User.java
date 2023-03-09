package com.cognizant.fems.model;

public class User {

    private int id;
    private String username;
    private String profileimg;

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

    public User (){ }

    public User(int id, String username, String profileimg){
        this.id = id;
        this.username = username;
        this.profileimg = profileimg;
    }
}
