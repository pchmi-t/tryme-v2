package org.fmi.tryme.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final UserServiceFactory userServiceFactory;

	@Autowired
	public UserController(UserServiceFactory userServiceFactory) {
		this.userServiceFactory = userServiceFactory;
	}

	@RequestMapping(value = "/api/v1/user", method = RequestMethod.GET)
	public AuthenticatedUser getUser() {
		UserService userService = userServiceFactory.create();
		return userService.getUser();
	}
}
