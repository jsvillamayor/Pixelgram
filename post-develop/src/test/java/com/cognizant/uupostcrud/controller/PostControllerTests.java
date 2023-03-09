package com.cognizant.uupostcrud.controller;

import com.cognizant.uupostcrud.Controllers.PostController;
import com.cognizant.uupostcrud.Model.PageOfItems;
import com.cognizant.uupostcrud.Model.Post;
import com.cognizant.uupostcrud.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostControllerTests {
    private PostController postController;

    @Mock
    private PostService postService;

    @BeforeEach
    public void init() {
        //injecting postService mock into postController
        MockitoAnnotations.openMocks(this);
        this.postController = new PostController(postService);
    }

    @Test
    public void getPage_doesNotReturnNull(){
        //arrange
        //when postService.getPage is called with pageNumber 1 & size 5, return a new PageOfItems<Post>
        when(postService.getPage(1,5)).thenReturn(new PageOfItems<Post>());

        //assert
        //assert that calling postController.getPage(1,5) does not return null (it returns new PageOfItems<Post>)
        assertNotNull(postController.getPage(1, 5));
    }

    @Test
    public void getPage_callsServiceGetPageMethod(){
        //arrange
        int pageNumber = 1;
        int size = 5;

        //act
        postController.getPage(pageNumber, size);

        //assert
        //verify postService.getPage() was called with pageNumber & size when calling postController.getPage(pageNumber,size)
        verify(postService).getPage(pageNumber, size);
    }

    @Test
    public void getPage_returnsPageOfItemsFromPostService(){
        //arrange
        int pageNumber = 1;
        int size = 1;

        Post post = new Post();
        List<Post> testList = new ArrayList<Post>();
        testList.add(post);

        PageOfItems<Post> page = new PageOfItems<Post>(testList, false, 1);

        //when postService.getPage(pageNumber,size) is called, return page
        when(postService.getPage(pageNumber, size)).thenReturn(page);

        //act
        //calling postController.getPage(pageNumber, size) so that it calls postService.getPage()
        PageOfItems<Post> result = postController.getPage(pageNumber,size);

        //assert
        //asserting page and result (which is just page returned from postService.getPage()) must be equal
        assertEquals(page, result);
    }
}
