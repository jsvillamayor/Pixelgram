package com.cognizant.fems.feignClient;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;
import com.cognizant.fems.model.PostFrontEnd;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "uu-post-crud", url = "${post.url}")// develop: "${post.url}"  local: "localhost:8085"
public interface PostFeign {

    @GetMapping("/posts?pageNumber={pageNumber}&pageSize={pageSize}")
    public PageOfItems<Post> getPosts(@RequestParam int pageNumber, @RequestParam int pageSize);

}

