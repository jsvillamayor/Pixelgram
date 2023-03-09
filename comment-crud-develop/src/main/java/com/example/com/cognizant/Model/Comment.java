package com.example.com.cognizant.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    private int postid;
    private String username;
    private String body;
    private LocalDate created;

    public Comment() {}

    public Comment(int id, int postid, String username, String body, LocalDate created) {
        this.id = id;
        this.postid = postid;
        this.username = username;
        this.body = body;
        this.created = created;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPostid() {
        return this.postid;
    }
    public void setPostid(int postid) {

        this.postid = postid;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreated() {
        return this.created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
