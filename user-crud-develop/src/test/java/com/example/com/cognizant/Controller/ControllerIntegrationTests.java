
package com.example.com.cognizant.Controller;

import com.example.com.cognizant.Model.User;
import com.example.com.cognizant.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class ControllerIntegrationTests {

    private MockMvc mockMvc;

    @SpyBean
    private UserController userController;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).addPlaceholderValue("fems.url","http://35.239.7.52").build();
    }

    @Test
    public void getUser_shouldReturn200Status() throws Exception {
        when(userService.findUser(1)).thenReturn(new User());
        mockMvc.perform(get("/users/1")).andExpect(status().isOk());
    }

    @Test
    public void getUser_shouldCallUserServiceWithParams() throws Exception {
        int id = 1;

        when(userService.findUser(id)).thenReturn(new User());
        mockMvc.perform(get("/users/"+id)).andExpect(status().isOk());

        verify(userService).findUser(id);
    }
}

