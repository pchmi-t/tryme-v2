package org.fmi.tryme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@SpringBootApplication
public class Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//
				.antMatchers("/favicon.ico").permitAll();

		http.authorizeRequests()//
				.antMatchers("/login**").permitAll()//
				.antMatchers("/connect**").permitAll()//
				.anyRequest().authenticated();

		http.formLogin()//
				.loginPage("/login")//
				.successForwardUrl("/login")//
				.permitAll();

		http.csrf().disable();
	}

}
