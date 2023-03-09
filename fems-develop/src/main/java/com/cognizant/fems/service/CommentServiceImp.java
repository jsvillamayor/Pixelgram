package com.cognizant.fems.service;

import com.cognizant.fems.feignClient.CommentFeign;
import com.cognizant.fems.model.Comment;
import com.cognizant.fems.model.PageOfItems;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentFeign commentFeign;

    @Override
    public PageOfItems<Comment> getAllComments(int postid, int pageNumber, int pageSize){
        if(pageNumber < 0 || pageSize < 0){
            return new PageOfItems<>();
        }
        PageOfItems<Comment> comments = commentFeign.getComments(postid, pageNumber, pageSize);
        if(comments == null){
            return new PageOfItems<>();
        }
        return comments;
    }
}
