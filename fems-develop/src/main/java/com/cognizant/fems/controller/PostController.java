package com.cognizant.fems.controller;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;
import com.cognizant.fems.service.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/oldposts")
//@CrossOrigin(origins = "${ui.url}", allowCredentials = "true") //${ui.url}
@CrossOrigin
public class PostController {

    private PostServiceImp postServiceImp;

    @Autowired
    public PostController(PostServiceImp postServiceImp) {
        this.postServiceImp = postServiceImp;
        //add future services here, they'll all get Autowired at once
    }

    @GetMapping
    public PageOfItems<Post> getPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.postServiceImp.getAllPosts(pageNumber, pageSize);
    }

}

