package com.cognizant.fems.controller;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.PostFrontEnd;
import com.cognizant.fems.service.PostFrontEndServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
//@CrossOrigin(origins = "${ui.url}", allowCredentials = "true") //${ui.url}
@CrossOrigin
public class PostFrontEndController {

    private PostFrontEndServiceImp postFrontEndServiceImp;

    @Autowired
    public PostFrontEndController(PostFrontEndServiceImp postFrontEndServiceImp) {
        this.postFrontEndServiceImp = postFrontEndServiceImp;
        //add future services here, they'll all get Autowired at once
    }

    @GetMapping
    public PageOfItems<PostFrontEnd> getPostFrontEnds(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.postFrontEndServiceImp.getAllPostFrontEnd(pageNumber, pageSize);
    }

}
