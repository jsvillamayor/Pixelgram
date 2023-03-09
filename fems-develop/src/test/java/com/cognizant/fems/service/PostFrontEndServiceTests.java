package com.cognizant.fems.service;

import com.cognizant.fems.controller.CommentController;
import com.cognizant.fems.controller.PostController;
import com.cognizant.fems.controller.PostFrontEndController;
import com.cognizant.fems.controller.UserController;
import com.cognizant.fems.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"feign.hystrix.enabled=true"})
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class) //new line
@ContextConfiguration(classes = { WireMockConfiguration.class })
@AutoConfigureWireMock(port = 9091)
public class PostFrontEndServiceTests {
    @InjectMocks
    PostFrontEndServiceImp postFrontEndServiceImp;

    @Mock
    private PostServiceImp postService;

    @Mock
    private CommentServiceImp commentService;

    @Mock
    private UserServiceImp userService;

    PageOfItems<PostFrontEnd> postFrontEndList;

    private PostController postController;
    private CommentController commentController;
    private PostFrontEndController postFrontEndController;
    private UserController userController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.postController = new PostController(postService);
        this.commentController = new CommentController(commentService);
        this.postFrontEndController = new PostFrontEndController(postFrontEndServiceImp);
        this.userController = new UserController(userService);
    }

    @Test
    public void pageNumberLessThanZeroReturnsEmpty(){
        postFrontEndList = new PageOfItems<>();
        Assertions.assertEquals(postFrontEndServiceImp.getAllPostFrontEnd(-1,2), postFrontEndList);
    }

    @Test
    public void pageNumberZeroReturnsEmpty(){
        postFrontEndList = new PageOfItems<>();
        Assertions.assertEquals(postFrontEndServiceImp.getAllPostFrontEnd(0,0), postFrontEndList);
    }


    @Test
    public void getPostFrontEndBasicValues() {
        int pageNumber = 1;
        int pageSize = 1;

        Comment post1Comment1 = new Comment(1,1,"Andrew", "Awesome sauce", LocalDate.now());
        Comment post1Comment2 = new Comment(2,1,"Bob", "Bad", LocalDate.now());
        PageOfItems<Comment> post1Comments = new PageOfItems<>();
        post1Comments.setItems(new ArrayList<Comment>(Arrays.asList(post1Comment1, post1Comment2)));

        Comment post2Comment1 = new Comment(1,1,"Devin", "Testing", LocalDate.now());
        PageOfItems<Comment> post2Comments = new PageOfItems<>();
        post2Comments.setItems(new ArrayList<Comment>(Arrays.asList(post2Comment1)));

        User user = new User(1, "bob", "img");

        Post p1 = new Post(1, 1, "2", "3", LocalDate.now());
        Post p2 = new Post(2, 1, "2", "3", LocalDate.now());
        PageOfItems<Post> postItems = new PageOfItems<>();
        postItems.setItems(new ArrayList<Post>(Arrays.asList(p1, p2)));

        PostFrontEnd post1 = new PostFrontEnd(p1.getId(), p1.getImg(),
                p1.getDescription(), p1.getCreatedOn(), post2Comments);
        PostFrontEnd post2 = new PostFrontEnd(p2.getId(), p2.getImg(),
                p2.getDescription(), p2.getCreatedOn(), post2Comments);

        PageOfItems<PostFrontEnd> postFrontEnd = new PageOfItems<>();
        postFrontEnd.setItems(new ArrayList<PostFrontEnd>(Arrays.asList(post1, post2)));

        when(postService.getAllPosts(pageNumber, pageSize)).thenReturn(postItems);
        when(commentService.getAllComments(1, pageNumber, pageSize)).thenReturn(post2Comments);
        when(userService.getUser(1)).thenReturn(user);
        PageOfItems<PostFrontEnd> newPostFrontEnd = postFrontEndServiceImp.getAllPostFrontEnd(pageNumber, pageSize);
        assertEquals(postFrontEnd.getItems().get(0).getImg(), newPostFrontEnd.getItems().get(0).getImg());
        assertEquals(postFrontEnd.getItems().get(0).getId(), newPostFrontEnd.getItems().get(0).getId());
        assertEquals(postFrontEnd.getItems().get(0).getDescription(), newPostFrontEnd.getItems().get(0).getDescription());
    }



}
