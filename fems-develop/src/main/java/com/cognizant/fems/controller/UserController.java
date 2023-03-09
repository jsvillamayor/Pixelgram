package com.cognizant.fems.controller;

import com.cognizant.fems.model.User;
import com.cognizant.fems.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "${ui.url}", allowCredentials = "true")
@CrossOrigin
public class UserController {
    private UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp){
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public User getUsers(@RequestParam int id){
        return this.userServiceImp.getUser(id);
    }

}
