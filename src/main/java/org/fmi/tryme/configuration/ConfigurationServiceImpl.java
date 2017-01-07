package org.fmi.tryme.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Value("${rpc.url}")
	private String rpcServiceURL;

	@Override
	public String getRPCAdminURL() {
		return rpcServiceURL;
	}

}
