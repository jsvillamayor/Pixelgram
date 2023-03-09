package com.example.com.cognizant.Controller;

import com.example.com.cognizant.Model.User;
import com.example.com.cognizant.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${fems.url}", allowCredentials = "true")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id){
        return userService.findUser(id);
    }


}
