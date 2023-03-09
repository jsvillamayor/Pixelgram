package com.example.com.cognizant.Repo;

import com.example.com.cognizant.Model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends PagingAndSortingRepository<Comment,Integer> {
    Page<Comment> findAllByPostid(int postid, Pageable pageable);
}
