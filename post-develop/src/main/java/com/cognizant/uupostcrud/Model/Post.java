package com.cognizant.uupostcrud.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private int userid;
    private String img;
    private String description;
    private LocalDate created;

    public Post(int id, int userid, String img, String description, LocalDate createdOn) {
        this.id = id;
        this.userid= userid;
        this.img = img;
        this.description = description;
        this.created = createdOn;
    }

    public Post() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid= userid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedOn() {
        return created;
    }

    public void setCreatedOn(LocalDate created) {
        this.created = created;
    }
}
