package com.cognizant.fems.feignClient;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;
import com.cognizant.fems.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "uu-user-crud", url = "${user.url}")//${user.url}
public interface UserFeign {
    @GetMapping("/users/{id}")
    public User getUsers(@RequestParam int id);
}
