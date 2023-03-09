package com.cognizant.uupostcrud.service;

import com.cognizant.uupostcrud.Model.PageOfItems;
import com.cognizant.uupostcrud.Model.Post;
import com.cognizant.uupostcrud.repo.PostRepo;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private PostRepo postRepository;

    public PostService(PostRepo postRepo){
        this.postRepository = postRepo;
    }

    public PageOfItems<Post> getPage(int pageNumber, int size){
        PageOfItems pageOfItems = new PageOfItems<>();
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("created").descending());
        pageOfItems.setHasNext(postRepository.findAll(pageable).hasNext());
        pageOfItems.setTotalElements((int) postRepository.findAll(pageable).getTotalElements());
        pageOfItems.setItems(postRepository.findAll(pageable).getContent());
        return  pageOfItems;
    }

    public Optional<Post> getById(int id){
        return postRepository.findById(id);
    }

}
