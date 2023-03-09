package com.cognizant.fems.feignClient;

import com.cognizant.fems.model.Comment;
import com.cognizant.fems.model.PageOfItems;
import com.cognizant.fems.service.CommentServiceImp;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"feign.hystrix.enabled=true"})
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfiguration.class })
@AutoConfigureWireMock(port = 9091)
public class CommentFeignTests {

    @Mock
    CommentFeign commentFeign;

    @InjectMocks
    CommentServiceImp commentServiceImp;

    PageOfItems<Comment> commentList;
    int postId = 1;
    int pageNumber = 1;
    int pageSize = 5;

    @BeforeEach
    public void init(){
        Comment comment = new Comment(1, 1, "User", "This was cool", LocalDate.now());
        ArrayList<Comment> testComments = new ArrayList<>();
        testComments.add(comment);
        commentList = new PageOfItems<Comment>(testComments, false,1);
    }

    @Test
    public void test() throws Exception{
        Mockito.when(commentFeign.getComments(postId,pageNumber,pageSize)).thenReturn(commentList);
        PageOfItems<Comment> comments = commentServiceImp.getAllComments(postId, pageNumber, pageSize);
        assertEquals(comments, commentList);
    }
}
