package com.cognizant.uupostcrud.controller;

import com.cognizant.uupostcrud.Controllers.PostController;
import com.cognizant.uupostcrud.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PostController.class)
@ExtendWith(SpringExtension.class)
public class PostControllerIntegrationTests {
    private MockMvc mockMvc;

    @SpyBean
    private PostController postController;

    @MockBean
    private PostService postService;

    @BeforeEach
    public void init() {
        //this is injecting anything that needs to be injected into this test class (in this case, postService)
        MockitoAnnotations.openMocks(this);
        //building postController !!! WITH THE FEMS.URL VALUE FROM .YML !!!
        mockMvc = MockMvcBuilders.standaloneSetup(postController).addPlaceholderValue("fems.url","http://35.239.7.52").build();

    }

    /*@Test
    public void test_returns200Status() throws Exception {
        //act
        mockMvc.perform(get("/post?pageNumber=1")).andExpect(status().isOk());
        //assert
    }*/

    @Test
    public void getPage_returns200Status() throws Exception {
        //act (what you're doing) & assert (what you want it to do)
        // perform GET request for PageOfItems<Post> and expect it to be successful
        mockMvc.perform(get("/posts?pageNumber=0&pageSize=1")).andExpect(status().isOk());
    }

    @Test
    public void getPage_callsPostServiceWithRequestParams() throws Exception {
        //arrange (set up / mocking data for test)
        int pageNumber = 1;
        int size = 1;

        //act
        //perform GET request for PageOfItems<Post> using passed in parameters and expect it to be successful
        mockMvc.perform(get("/posts?pageNumber="+pageNumber+"&pageSize="+size)).andExpect(status().isOk());

        //assert
        //verify that postService method was called with the correct params when performing the GET request above
        verify(postService).getPage(pageNumber, size);
    }
}
