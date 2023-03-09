package com.cognizant.fems.service;

import com.cognizant.fems.feignClient.CommentFeign;
import com.cognizant.fems.model.Comment;
import com.cognizant.fems.model.PageOfItems;
import org.junit.jupiter.api.Assertions;
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
public class CommentServiceImpTests {

    @InjectMocks
    CommentServiceImp commentServiceImp;

    @Mock
    CommentFeign commentFeign;

    PageOfItems<Comment> commentList;

    @Test
    public void pageNumberLessThanZeroReturnsEmpty(){
        commentList = new PageOfItems<>();
        Assertions.assertEquals(commentServiceImp.getAllComments(1,-1, 2), commentList);
    }

    @Test
    public void pageNumberZeroReturnsEmpty(){
        commentList = new PageOfItems<>();
        if(commentFeign == null){
            System.out.printf("commentFeign is null");
        }
        Assertions.assertEquals(commentServiceImp.getAllComments(0, 0, 0), commentList);
    }

    @Test
    public void getCommentsServiceReturnsBasicValues() {
        int pageNumber = 1;
        int pageSize = 1;
        commentList = new PageOfItems<>();

        Comment comment1 = new Comment(1, 1, "wow");
        Comment comment2 = new Comment(2, 1, "wow");
        Comment comment3 = new Comment(3, 1, "wow");
        commentList.setItems(new ArrayList<Comment>(Arrays.asList(comment1, comment2, comment3)));

        when(commentFeign.getComments(1, pageNumber, pageSize)).thenReturn(commentList);
        PageOfItems<Comment> newCommentList = commentServiceImp.getAllComments(1, pageNumber, pageSize);
        assertEquals(commentList, newCommentList);
    }

}
