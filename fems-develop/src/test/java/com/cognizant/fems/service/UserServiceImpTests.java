package com.cognizant.fems.service;


import com.cognizant.fems.feignClient.UserFeign;
import com.cognizant.fems.model.User;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"feign.hystrix.enabled=true"})
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class) //new line
@ContextConfiguration(classes = { WireMockConfiguration.class })
@AutoConfigureWireMock(port = 9091)
public class UserServiceImpTests {

    @InjectMocks
    UserServiceImp userServiceImp;

    @Mock
    UserFeign userFeign;

    User user;

    @Test
    public void userDetailsAsExpected(){
        user = new User(1, "boba", "img");
        assertEquals(1, user.getId());
        assertEquals("boba", user.getUsername());
        assertEquals("img", user.getProfileimg());
    }

    @Test
    public void getUsersServiceReturnsBasicValue(){
        user = new User(1, "bob", "img");
        when(userFeign.getUsers(1)).thenReturn(user);
        User newUser = userServiceImp.getUser(1);
        assertEquals(user, newUser);
    }
}
