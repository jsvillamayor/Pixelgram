package com.example.com.cognizant.Repo;

import com.example.com.cognizant.Model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
    public User findById(int id);
}
