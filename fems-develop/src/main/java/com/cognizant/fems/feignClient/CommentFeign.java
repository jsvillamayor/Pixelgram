package com.cognizant.fems.feignClient;

import com.cognizant.fems.model.Comment;
import com.cognizant.fems.model.PageOfItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "uu-comment-crud", url = "${comment.url}")//${comment.url} when deployed
public interface CommentFeign {

    @GetMapping("/comments?postid={postid}&pageNumber={pageNumber}&pageSize={pageSize}")
    public PageOfItems<Comment> getComments(@RequestParam int postid, @RequestParam int pageNumber, @RequestParam int pageSize);
}
