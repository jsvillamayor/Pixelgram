package com.example.com.cognizant;

import com.example.com.cognizant.Model.User;
import com.example.com.cognizant.Repo.UserRepo;
import com.example.com.cognizant.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


class UserCrudApplicationTests {

	private UserService userService;

	@Mock
	private UserRepo userRepo;

	@BeforeEach
	public void init(){
		MockitoAnnotations.openMocks(this);
		this.userService = new UserService(userRepo);
	}

	@Test
	public void findUser_shouldNotReturnNull(){
		when(userRepo.findById(Mockito.anyInt())).thenReturn(new User(1,"username1","profileImg1"));

		assertNotNull(userService.findUser(1));
	}

	@Test
	public void findUser_shouldCallUserRepoByUserId(){
		User user=new User(2,"username2","Img2");
		when(userRepo.findById(Mockito.anyInt())).thenReturn(user);
		assertEquals(2, user.getId());
	}
}

