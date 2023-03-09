package com.cognizant.fems.service;

import com.cognizant.fems.feignClient.UserFeign;
import com.cognizant.fems.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    @Autowired
    private UserFeign userFeign;

    @Override
    public User getUser(int id){
        if(id < 0){
            return new User();
        }
        User user = userFeign.getUsers(id);
        return user;
    }
}
