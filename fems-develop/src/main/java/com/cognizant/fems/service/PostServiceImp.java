package com.cognizant.fems.service;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;
import com.cognizant.fems.feignClient.PostFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService{
    @Autowired
    private PostFeign postFeign;

    @Override
    public PageOfItems<Post> getAllPosts(int pageNumber, int pageSize) {
        if(pageNumber < 0 || pageSize < 0)
            return new PageOfItems<>();
        PageOfItems<Post> posts = postFeign.getPosts(pageNumber, pageSize);
        if(posts == null)
            return new PageOfItems<>();
        return posts;
    }
}
