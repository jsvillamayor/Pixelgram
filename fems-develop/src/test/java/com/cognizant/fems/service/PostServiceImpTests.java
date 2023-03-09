package com.cognizant.fems.service;

import com.cognizant.fems.feignClient.PostFeign;
import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"feign.hystrix.enabled=true"})
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class) //new line
@ContextConfiguration(classes = { WireMockConfiguration.class })
@AutoConfigureWireMock(port = 9091)
public class PostServiceImpTests {

    @InjectMocks
    PostServiceImp psi;

    @Mock
    PostFeign postFeign;

    PageOfItems<Post> postList;

    /*@BeforeEach
    public void init() {
        psi = new PostServiceImp();

        /*Post post = new Post(1,"Andrew", "Image here",
                "this is a post", LocalDate.now());

        ArrayList<Post> testPosts = new ArrayList<Post>(){{add(post);}};
        postList = new PageOfItems<Post>(testPosts, false, 1);*/
    //}

    @Test
    public void pageNumberLessThanZeroReturnsEmpty() {
        postList = new PageOfItems<>();
        assertEquals(psi.getAllPosts(-1, 2), postList);
    }
    @Test
    public void pageNumberZeroReturnsEmpty() {
        postList = new PageOfItems<>();
        if(postFeign == null)
            System.out.println("postFeign is null");
        assertEquals(psi.getAllPosts(0, 0), postList);
    }

    @Test
    public void getPostsServiceReturnsBasicValues() {
        int pageNumber = 1;
        int pageSize = 1;
        postList = new PageOfItems<>();

        Post post1 = new Post(1,1, "Image here",
                "this is a post1", LocalDate.now());
        Post post2 = new Post(2,2, "Image here",
                "this is a post2", LocalDate.now());
        Post post3 = new Post(3,3, "Image here",
                "this is a post3", LocalDate.now());
        postList.setItems(new ArrayList<Post>(Arrays.asList(post1, post2, post3)));

        when(postFeign.getPosts(pageNumber, pageSize)).thenReturn(postList);
        PageOfItems<Post> newPostList = psi.getAllPosts(pageNumber, pageSize);
        assertEquals(postList, newPostList);
    }
}
