package com.example.com.cognizant.Service;

import com.example.com.cognizant.Model.User;
import com.example.com.cognizant.Repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    public User findUser(int id){
        return userRepo.findById(id);
    }
}
