package com.example.com.cognizant.controller;

import com.example.com.cognizant.Controllers.CommentController;
import com.example.com.cognizant.Service.CommentService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@ExtendWith(SpringExtension.class)
public class ControllerIntegrationTests {

    private MockMvc mockMvc;

    @SpyBean
    private CommentController commentController;

    @MockBean
    private CommentService commentService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).addPlaceholderValue("fems.url","http://35.239.7.52").build();

    }


    @Test
    public void getPage_returns200Status() throws Exception {
        mockMvc.perform(get("/comments?postid=0&pageNumber=0&pageSize=1")).andExpect(status().isOk());
    }

    @Test
    public void getPage_callsCommentServiceWithRequestParams() throws Exception {
        //arrange
        int postid = 0;
        int pageNumber = 0;
        int size = 1;

        //act
        mockMvc.perform(get("/comments?postid="+postid+"&pageNumber="+pageNumber+"&pageSize="+size));

        //assert
        verify(commentService).getComments(postid, pageNumber, size);
    }

}
