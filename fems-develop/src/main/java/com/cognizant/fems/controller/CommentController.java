package com.cognizant.fems.controller;

import com.cognizant.fems.model.Comment;
import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.service.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
//@CrossOrigin(origins = "${ui.url}", allowCredentials = "true")//${ui.url}
@CrossOrigin
public class CommentController {
    private CommentServiceImp commentServiceImp;

    @Autowired
    public CommentController(CommentServiceImp commentServiceImp){
        this.commentServiceImp = commentServiceImp;
    }

    @GetMapping
    public PageOfItems<Comment> getComments(@RequestParam int postid,@RequestParam int pageNumber,@RequestParam int pageSize){
        return this.commentServiceImp.getAllComments(postid, pageNumber, pageSize);
    }
}
