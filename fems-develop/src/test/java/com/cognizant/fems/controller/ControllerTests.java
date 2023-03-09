package com.cognizant.fems.controller;

import com.cognizant.fems.model.*;
import com.cognizant.fems.service.CommentServiceImp;
import com.cognizant.fems.service.PostFrontEndServiceImp;
import com.cognizant.fems.service.PostServiceImp;
import com.cognizant.fems.service.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerTests {
    private PostController postController;

    @Mock
    private PostServiceImp postService;

    private CommentController commentController;

    @Mock
    private CommentServiceImp commentService;

    private PostFrontEndController postFrontEndController;

    @Mock
    private PostFrontEndServiceImp postFrontEndService;

    private UserController userController;

    @Mock
    private UserServiceImp userService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.postController = new PostController(postService);
        this.postFrontEndController = new PostFrontEndController(postFrontEndService);
        this.commentController = new CommentController(commentService);
        this.userController = new UserController(userService);
    }

    @Test
    public void getPostsDoesNotReturnNull(){
        Mockito.when(postService.getAllPosts(1,1)).thenReturn(new PageOfItems<Post>());
        assertNotNull(postController.getPosts(1, 1));
    }

    @Test
    public void getCommentsDoesNotReturnNull(){
        Mockito.when(commentService.getAllComments(1, 1, 1)).thenReturn(new PageOfItems<Comment>());
        assertNotNull(commentController.getComments(1, 1, 1));
    }

    @Test
    public void getPostFrontEndsDoesNotReturnNull(){
        Mockito.when(postFrontEndService.getAllPostFrontEnd(1, 1)).thenReturn(new PageOfItems<PostFrontEnd>());
        assertNotNull(postFrontEndController.getPostFrontEnds(1,1));
    }

    @Test
    public void getUsersDoesNotReturnNull(){
        Mockito.when(userService.getUser(1)).thenReturn(new User());
        assertNotNull(userController.getUsers(1));
    }


    @Test
    public void getPostsReturnsBasicValues() {

        int pageNumber = 2;
        int pageSize = 4;
        PageOfItems<Post> postList = new PageOfItems<>();

        Post post1 = new Post(1,1, "Image here",
                "this is a post1", LocalDate.now());
        Post post2 = new Post(2,2, "Image here",
                "this is a post2", LocalDate.now());
        Post post3 = new Post(3,3, "Image here",
                "this is a post3", LocalDate.now());
        postList.setItems(new ArrayList<Post>(Arrays.asList(post1, post2, post3)));

        when(postService.getAllPosts(pageNumber, pageSize)).thenReturn(postList);
        PageOfItems<Post> newPostList = postController.getPosts(pageNumber, pageSize);
        assertEquals(postList, newPostList);
    }

    @Test
    public void getCommentsReturnsBasicValues(){
        int pageNumber = 2;
        int pageSize = 4;

        Comment post1Comment1 = new Comment(1,1,"Andrew", "Awesome sauce", LocalDate.now());
        Comment post1Comment2 = new Comment(2,1,"Bob", "Bad", LocalDate.now());
        PageOfItems<Comment> post1Comments = new PageOfItems<>();
        post1Comments.setItems(new ArrayList<Comment>(Arrays.asList(post1Comment1, post1Comment2)));

        when(commentService.getAllComments(1,pageNumber, pageSize)).thenReturn(post1Comments);
        PageOfItems<Comment> newCommentList = commentController.getComments(1, pageNumber, pageSize);
        assertEquals(post1Comments, newCommentList);
    }


    @Test
    public void getPostFrontEndBasicValues() {
        int pageNumber = 1;
        int pageSize = 1;

        Comment post1Comment1 = new Comment(1,1,"Andrew", "Awesome sauce", LocalDate.now());
        Comment post1Comment2 = new Comment(2,1,"Bob", "Bad", LocalDate.now());
        PageOfItems<Comment> post1Comments = new PageOfItems<>();
        post1Comments.setItems(new ArrayList<Comment>(Arrays.asList(post1Comment1, post1Comment2)));

        Comment post2Comment1 = new Comment(1,2,"Devin", "Testing", LocalDate.now());
        PageOfItems<Comment> post2Comments = new PageOfItems<>();
        post2Comments.setItems(new ArrayList<Comment>(Arrays.asList(post2Comment1)));

        PostFrontEnd post1 = new PostFrontEnd(1, "Image here",
                "this is a post1", LocalDate.now(), post1Comments);
        PostFrontEnd post2 = new PostFrontEnd(1, "Image here",
                "this is a post2", LocalDate.now(), post2Comments);

        PageOfItems<PostFrontEnd> postFrontEnd = new PageOfItems<>();
        postFrontEnd.setItems(new ArrayList<PostFrontEnd>(Arrays.asList(post1, post2)));

        when(postFrontEndService.getAllPostFrontEnd(pageNumber,pageSize)).thenReturn(postFrontEnd);
        PageOfItems<PostFrontEnd> newPostFrontEnd = postFrontEndController.getPostFrontEnds(pageNumber,pageSize);
        assertEquals(postFrontEnd, newPostFrontEnd);
    }

    @Test
    public void getUsersReturnsBasicValues(){
        User user = new User(1, "Bob", "image");

        when(userService.getUser(1)).thenReturn(user);
        User newUser = userController.getUsers(1);
        assertEquals(user, newUser);
    }

    @Test
    public void getPaginatedPosts() {
        int pageNumber = 2;
        int pageSize = 4;

        postController.getPosts(pageNumber, pageSize);
        verify(postService).getAllPosts(pageNumber, pageSize);
    }
}
