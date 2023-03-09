package com.cognizant.fems.service;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.PostFrontEnd;

public interface PostFrontEndService {
    PageOfItems<PostFrontEnd> getAllPostFrontEnd(int pageNumber, int pageSize);
}
