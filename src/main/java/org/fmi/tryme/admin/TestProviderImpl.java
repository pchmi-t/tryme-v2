package org.fmi.tryme.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.fmi.tryme.admin.beans.Category;
import org.fmi.tryme.admin.beans.TestDescription;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.http.NotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class TestProviderImpl implements TestProvider {

	private final Gson gson = new Gson();

	@Override
	public Set<Category> getCategories() {
		/**
		 * implement for real as soon as the other component API is ready to be
		 * called
		 */
		try {
			String json = Resources.toString(Resources.getResource("data/categories.json"), Charsets.UTF_8);
			return gson.fromJson(json, new TypeToken<Set<Category>>() {
			}.getType());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

	@Override
	public Set<TestDescription> getTests(String categoryId) {
		/**
		 * implement for real as soon as the other component API is ready to be
		 * called
		 */
		try {
			String json = Resources.toString(Resources.getResource("data/test_descriptions.json"), Charsets.UTF_8);
			Set<TestDescription> testDescriptions = gson.fromJson(json, new TypeToken<Set<TestDescription>>() {
			}.getType());
			for (Iterator<TestDescription> iterator = testDescriptions.iterator(); iterator.hasNext();) {
				TestDescription testDescription = (TestDescription) iterator.next();
				if (!categoryId.equals(testDescription.getCategory().getId())) {
					iterator.remove();
				}

			}
			return testDescriptions;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Trytest getTest(String testID) {
		try {
			String json = Resources.toString(Resources.getResource("data/tests.json"), Charsets.UTF_8);
			Set<Trytest> testDescriptions = gson.fromJson(json, new TypeToken<Set<Trytest>>() {
			}.getType());
			for (Trytest trytest : testDescriptions) {
				if (testID.equals(trytest.getDescription().getId())) {
					return trytest;
				}
			}
			throw new NotFoundException("Cannot find test " + testID);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
