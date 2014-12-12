package com.xebia.tdd.training.legacy3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UserService.class })
public class UserServiceTest {

	private UserDAO userDAO;

	@org.junit.Before
	public void setup() {
		userDAO = Mockito.mock(UserDAO.class);
	}

	// @Test
	public void shouldSaveUserCorrectly_2() {

		UserService userService = Mockito.mock(UserService.class);
		userService.setUserDAO(userDAO);

		// Mockito.doReturn(true).when(userService).sendEmailToUser((User)Mockito.anyObject());
		Mockito.when(userService.saveUser((User) Mockito.anyObject()))
				.thenCallRealMethod();

		userService.saveUser(new User());
		
		//Verification
	}

	// @Test
	public void shouldSaveUserCorrectly_1() {
		UserService userService = new UserService();
		userService.setUserDAO(userDAO);
		UserService serviceSpy = Mockito.spy(userService);

		// Mockito.doReturn(true).when(serviceSpy).sendEmailToUser((User)Mockito.anyObject());

		serviceSpy.saveUser(new User());
		
		//Verification
	}

	@Test
	public void shouldSaveUserCorrectly_3() throws Exception {

		UserService userService = PowerMockito.mock(UserService.class);
		userService.setUserDAO(userDAO);

		PowerMockito.doReturn(true).when(userService, "sendEmailToUser",
				(User) Mockito.anyObject());
		PowerMockito.when(userService.saveUser((User) Mockito.anyObject()))
				.thenCallRealMethod();

		userService.saveUser(new User());
		
		//Verification
	}
}
