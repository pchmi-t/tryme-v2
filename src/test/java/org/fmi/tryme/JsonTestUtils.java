package org.fmi.tryme;

import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

public class JsonTestUtils {
	
	private static final Gson GSON = new Gson();

	private JsonTestUtils() {

	}
	
	public static <T> T readJson(String jsonFile, Class<T> classOfT) throws IOException {
		String json = Resources.toString(Resources.getResource(jsonFile), Charsets.UTF_8);
		return GSON.fromJson(json, classOfT);
	}

}
