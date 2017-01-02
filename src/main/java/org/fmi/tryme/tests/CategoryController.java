package org.fmi.tryme.tests;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@RequestMapping(method = RequestMethod.GET)
	public Set<Category> retrieveCategories() {
		return new HashSet<>();
	}

}
