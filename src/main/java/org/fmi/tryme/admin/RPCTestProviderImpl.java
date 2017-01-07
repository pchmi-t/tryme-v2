package org.fmi.tryme.admin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.fmi.tryme.admin.beans.Category;
import org.fmi.tryme.admin.beans.CategoryDTO;
import org.fmi.tryme.admin.beans.DescriptionDTO;
import org.fmi.tryme.admin.beans.TestDescription;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.admin.beans.TrytestDTO;
import org.fmi.tryme.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

import net.minidev.json.JSONObject;

@Service
public class RPCTestProviderImpl implements TestProvider {

	private static final String RPC_REQUEST_RESULT = "result";

	private final Gson gson = new Gson();

	private final URL rpcAdminServiceURL;

	private JSONRPC2Session session;

	@Autowired
	public RPCTestProviderImpl(ConfigurationService configurationService) {
		try {
			this.rpcAdminServiceURL = new URL(configurationService.getRPCAdminURL());
		} catch (MalformedURLException e) {
			throw new TestProviderException("Could not initialize session to " + configurationService.getRPCAdminURL(),
					e);
		}

	}

	@Override
	public Set<Category> getCategories() {
		JSONObject response = callRPCAdminService("all_categories");
		Set<CategoryDTO> dto = responseToObject(response, new TypeToken<Set<CategoryDTO>>() {
		});
		return dto.stream().map(CategoryDTO::toCategory).collect(Collectors.toSet());
	}

	@Override
	public Set<TestDescription> getTests(String categoryId) {
		JSONObject response = callRPCAdminService("tests_for_category", categoryId);
		Set<DescriptionDTO> dtos = responseToObject(response, new TypeToken<Set<DescriptionDTO>>() {
		});
		return dtos.stream().map(DescriptionDTO::toDescription).collect(Collectors.toSet());
	}

	@Override
	public Trytest getTest(String testID) {
		JSONObject response = callRPCAdminService("get_test", testID);
		TrytestDTO dto = responseToObject(response, new TypeToken<TrytestDTO>() {
		});
		return dto.toTrytest();
	}

	private JSONObject callRPCAdminService(String method, Object... parameters) {
		JSONRPC2Session rpcSession = getJSONRPC2Session();
		JSONRPC2Request request = new JSONRPC2Request(method, Arrays.asList(parameters), 0);
		try {
			JSONRPC2Response response = rpcSession.send(request);
			if (!response.indicatesSuccess()) {
				throw new TestProviderException("Could not execute request to " + method);
			}
			return response.toJSONObject();
		} catch (JSONRPC2SessionException e) {
			throw new TestProviderException("Could not execute request to " + method, e);
		}
	}

	private <T> T responseToObject(JSONObject response, TypeToken<T> type) {
		Object result = response.get(RPC_REQUEST_RESULT);
		return gson.fromJson(result.toString(), type.getType());
	}

	private synchronized JSONRPC2Session getJSONRPC2Session() {
		if (session == null) {
			session = new JSONRPC2Session(rpcAdminServiceURL);
		}
		return session;
	}

}
