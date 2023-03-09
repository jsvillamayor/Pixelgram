
package com.example.com.cognizant.Controller;

import com.example.com.cognizant.Model.User;
import com.example.com.cognizant.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerUnitTests {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.userController = new UserController(userService);
    }

    @Test
    public void getUser_shouldNotReturnNull(){
        when(userService.findUser(1)).thenReturn(new User());
        assertNotNull(userController.getUser(1));
    }

    @Test
    public void getUser_shouldCallServiceGetUserMethod(){
        int id = 0;

        userController.getUser(id);

        verify(userService).findUser(id);
    }

    @Test
    public void getUser_shouldReturnUserFromUserService(){
        int id = 0;
        User user = new User();

        when(userService.findUser(id)).thenReturn(user);

        User result = userController.getUser(id);

        assertEquals(user,result);
    }
}

