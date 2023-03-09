package com.cognizant.fems.service;

import com.cognizant.fems.model.Comment;
import com.cognizant.fems.model.PageOfItems;

public interface CommentService {
    PageOfItems<Comment> getAllComments(int postid, int pageNumber, int pageSize);
}
