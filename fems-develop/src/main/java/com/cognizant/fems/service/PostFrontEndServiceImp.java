package com.cognizant.fems.service;

import com.cognizant.fems.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFrontEndServiceImp implements PostFrontEndService {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Override
    public PageOfItems<PostFrontEnd> getAllPostFrontEnd(int pageNumber, int pageSize){
        PageOfItems<Post> posts = postService.getAllPosts(pageNumber, pageSize);
        List<PostFrontEnd> pfeList = new ArrayList<>();
        int i;
        if(posts != null){
            for(i = 0; i < posts.getItems().size(); i++){
                Post post = posts.getItems().get(i);
                System.out.printf("%d\n", post.getUserId());
                User user = userService.getUser(post.getUserId());
                PageOfItems<Comment> comments = commentService.getAllComments(post.getId(), 0, 5);
                PostFrontEnd temp = new PostFrontEnd(post.getId(), user, post.getImg(), post.getDescription(), post.getCreatedOn(), comments);
                pfeList.add(temp);
            }
            PageOfItems<PostFrontEnd> postFrontEnd = new PageOfItems<>();
            postFrontEnd.setItems(pfeList);
            return postFrontEnd;
        } else{
            return new PageOfItems<>();
        }
    }

}