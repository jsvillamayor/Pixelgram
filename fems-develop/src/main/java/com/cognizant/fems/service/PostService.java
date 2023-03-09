package com.cognizant.fems.service;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;

public interface PostService {
    PageOfItems<Post> getAllPosts(int pageNumber, int pageSize);
}
