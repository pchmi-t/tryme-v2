package org.fmi.tryme.user;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value="/api/user", method=RequestMethod.GET)
	public UserDTO user(Principal principal) {
		return new UserDTO(principal.getName());
	}

}
