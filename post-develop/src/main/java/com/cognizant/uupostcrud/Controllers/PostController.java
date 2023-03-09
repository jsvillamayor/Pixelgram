package com.cognizant.uupostcrud.Controllers;

import com.cognizant.uupostcrud.Model.PageOfItems;
import com.cognizant.uupostcrud.Model.Post;
import com.cognizant.uupostcrud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin(origins = "${fems.url}", allowCredentials = "true")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){

        this.postService = postService;
    }

    /*
     * GET request to troubleshoot tests.
     *
    @RequestMapping(value = "/post", method = GET)
    @ResponseBody
    public PageOfItems<Post> test(@RequestParam("pageNumber") int pageNumber){
        return webService.getPage(pageNumber, 5);
    }*/

    /*
     * GET request for pages of posts.
     * url for this would be /post?pageNumber={pageNumber}&pageSize={pageSize}, where ? indicates requested params
     */
    @RequestMapping(value = "/posts", params = {"pageNumber", "pageSize"}, method = GET)
    @ResponseBody
    public PageOfItems<Post> getPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int size){
        return postService.getPage(pageNumber, size);
    }


}
