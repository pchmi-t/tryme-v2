package org.fmi.tryme.user;

import java.util.Locale;

import org.springframework.social.facebook.api.User;

public class AuthenticatedUser {

	private final String id;
	private final String name;
	private final String email;
	private final String gender;
	private final String firstName;
	private final Locale locale;

	public AuthenticatedUser(User userProfile) {
		this.id = userProfile.getId();
		this.locale = userProfile.getLocale();
		this.name = userProfile.getName();
		this.email = userProfile.getEmail();
		this.gender = userProfile.getGender();
		this.firstName = userProfile.getFirstName();
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getId() {
		return id;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenticatedUser [id=").append(id).append(", name=").append(name).append(", email=")
				.append(email).append(", gender=").append(gender).append(", firstName=").append(firstName)
				.append(", locale=").append(locale).append("]");
		return builder.toString();
	}

}
