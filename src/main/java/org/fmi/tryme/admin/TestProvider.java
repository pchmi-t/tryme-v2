package org.fmi.tryme.admin;

import java.util.Set;

import org.fmi.tryme.admin.beans.Category;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.admin.beans.TestDescription;

public interface TestProvider {
	
	Set<Category> getCategories();
	
	Set<TestDescription> getTests(String category);
	
	Trytest getTest(String testID);

}
