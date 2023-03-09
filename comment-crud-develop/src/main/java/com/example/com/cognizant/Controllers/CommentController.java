package com.example.com.cognizant.Controllers;

import com.example.com.cognizant.Model.Comment;
import com.example.com.cognizant.Model.PageOfItems;
import com.example.com.cognizant.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@CrossOrigin(origins = "${fems.url}", allowCredentials = "true")
public class CommentController {
    //adding comment to test pipeline
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){

        this.commentService=commentService;
    }

    @RequestMapping(value = "/comments", params = {"postid","pageNumber", "pageSize"}, method = GET)
    @ResponseBody
    public PageOfItems<Comment> getPage(@RequestParam("postid") int postId,@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int size) {
        return commentService.getComments(postId,pageNumber, size);

    }

}
