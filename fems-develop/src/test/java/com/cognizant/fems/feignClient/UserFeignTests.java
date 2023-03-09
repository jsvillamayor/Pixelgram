package com.cognizant.fems.feignClient;

import com.cognizant.fems.model.User;
import com.cognizant.fems.service.UserServiceImp;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"feign.hystrix.enabled=true"})
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfiguration.class })
@AutoConfigureWireMock(port = 9091)
public class UserFeignTests {

    @Mock
    UserFeign userFeign;

    @InjectMocks
    UserServiceImp userServiceImp;

    @Test
    public void test() throws Exception{
        User user = new User(1, "bob", "img");
        Mockito.when(userFeign.getUsers(1)).thenReturn(user);
        User newUser = userServiceImp.getUser(1);
        assertEquals(user, newUser);
    }
}
