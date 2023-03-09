package com.cognizant.fems.model;

import java.time.LocalDate;

public class Comment {
    private int id;
    private int postid;
    private String username;
    private String body;
    private LocalDate created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Comment(){

    }

    public Comment(int id, int postid, String body){
        this.id = id;
        this.postid = postid;
        this.body = body;
    }

    public Comment(int id, int postid, String username, String body, LocalDate created){
        this.id = id;
        this.postid = postid;
        this.username = username;
        this.body = body;
        this.created = created;
    }
}
