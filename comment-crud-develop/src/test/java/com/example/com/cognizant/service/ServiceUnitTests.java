package com.example.com.cognizant.Service;

import com.example.com.cognizant.Model.Comment;
import com.example.com.cognizant.Model.PageOfItems;
import com.example.com.cognizant.Repo.CommentRepo;
import com.example.com.cognizant.Service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ServiceUnitTests {

    private CommentService commentService;

    @Mock
    private CommentRepo commentRepo;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.commentService = new CommentService(commentRepo);
    }

    @Test
    public void getPage_shouldNotReturnNull(){
        Page<Comment> page = Mockito.mock(Page.class);

        when(commentRepo.findAllByPostid(Mockito.anyInt(), Mockito.any())).thenReturn(page);

        assertNotNull(commentService.getComments(0,0,1));
    }

    @Test
    public void getPage_shouldCallCommentRepoFindAllByPostId(){
        Page<Comment> page = Mockito.mock(Page.class);

        when(commentRepo.findAllByPostid(Mockito.anyInt(), Mockito.any())).thenReturn(page);

        assertEquals(commentService.getComments(0, 0, 1).getTotalElements(), page.getTotalElements());
    }
}
