package org.fmi.tryme.user;

import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class UserService {

	private final FacebookTemplate facebook;

	public UserService(FacebookTemplate facebook) {
		this.facebook = facebook;
	}

	public AuthenticatedUser getUser() {
		UserOperations userOperations = facebook.userOperations();
		return new AuthenticatedUser(userOperations.getUserProfile());
	}

}
