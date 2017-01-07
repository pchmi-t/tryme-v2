package org.fmi.tryme.user;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

	private static final String[] VALID_FACEBOOK_USER_FIELDS = { "id", "birthday", "context", "cover", "education",
			"email", "first_name", "gender", "hometown", "inspirational_people", "languages", "last_name", "link",
			"locale", "location", "middle_name", "name", "name_format" };

	@PostConstruct
	public void onStartup() {
		try {
			Field field = Class.forName("org.springframework.social.facebook.api.UserOperations")
					.getDeclaredField("PROFILE_FIELDS");
			field.setAccessible(true);

			Field modifiers = field.getClass().getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, VALID_FACEBOOK_USER_FIELDS);

		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
}
