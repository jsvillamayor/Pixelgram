package com.example.com.cognizant.Service;

import com.example.com.cognizant.Model.Comment;
import com.example.com.cognizant.Model.PageOfItems;
import com.example.com.cognizant.Repo.CommentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo){
        this.commentRepo = commentRepo;
    }

    //should be able to see comment count
    //should be ale to see up to 5 comments initially
    //should be able to view the next 5 comments with view more comments

    /*public Page<Comment> getCommentById(int postId) {
        return this.commentRepo.findAllByPostId(postId);
    }*/

    public PageOfItems<Comment> getComments(int postid, int pageNumber, int size){
        PageOfItems pageOfItems = new PageOfItems<>();
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("created").descending());
        pageOfItems.setHasNext(commentRepo.findAllByPostid(postid, pageable).hasNext());
        pageOfItems.setTotalElements((int) commentRepo.findAllByPostid(postid, pageable).getTotalElements());
        pageOfItems.setItems(commentRepo.findAllByPostid(postid, pageable).getContent());
        return pageOfItems;

    }
}
