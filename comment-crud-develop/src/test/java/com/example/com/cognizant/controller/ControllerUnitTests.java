package com.example.com.cognizant.controller;

import com.example.com.cognizant.Controllers.CommentController;
import com.example.com.cognizant.Model.Comment;
import com.example.com.cognizant.Model.PageOfItems;
import com.example.com.cognizant.Service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerUnitTests {

    private CommentController commentController;

    @Mock
    private CommentService commentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.commentController = new CommentController(commentService);
    }


    @Test
    public void getPage_doesNoteReturnNull(){
        //act
        when(commentService.getComments(0,0, 5)).thenReturn(new PageOfItems<Comment>());

        //assert
        assertNotNull(commentController.getPage(0,0,5));
    }

    @Test
    public void getPage_callsServiceGetPageMethod(){
        //arrange
        int postid = 0;
        int pageNumber = 0;
        int size = 5;

        //act
        commentController.getPage(postid, pageNumber, size);

        //assert
        verify(commentService).getComments(postid, pageNumber, size);
    }

    @Test
    public void getPage_returnsPageOfItemsFromCommentService(){
        //arrange
        int postId = 0;
        int pageNumber = 0;
        int size = 1;

        Comment comment = new Comment();
        List<Comment> list = new ArrayList<>();
        list.add(comment);

        PageOfItems<Comment> page = new PageOfItems<>(list, false, size);

        when(commentService.getComments(postId, pageNumber, size)).thenReturn(page);

        //act
        PageOfItems<Comment> result = commentController.getPage(postId, pageNumber, size);

        //assert
        assertEquals(page, result);

    }
}
