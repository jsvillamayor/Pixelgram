package com.cognizant.fems.feignClient;

import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.model.Post;
import com.cognizant.fems.service.PostServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest(properties = {"feign.hystrix.enabled=true"})
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfiguration.class })
@AutoConfigureWireMock(port = 9091)
public class PostFeignTests {

    @Mock
    PostFeign postFeign;

    @InjectMocks
    PostServiceImp postServiceImp;

    PageOfItems<Post> postList;
    int pageNumber;
    int pageSize;

    @BeforeEach
    public void init() {
        Post post = new Post(1,1, "Image here",
                "this is a post", LocalDate.now());

        ArrayList<Post> testPosts = new ArrayList<Post>(){{add(post);}};
        postList = new PageOfItems<Post>(testPosts, false, 1);
        postList = new PageOfItems<Post>(testPosts, false, 1);

        pageNumber = 1;
        pageSize = 5;
    }

    @Test
    public void basicTest() throws Exception {
        Mockito.when(postFeign.getPosts(pageNumber, pageSize)).thenReturn(postList);
        PageOfItems<Post> posts = postServiceImp.getAllPosts(pageNumber, pageSize);
        assertEquals(posts, postList);
    }

}
