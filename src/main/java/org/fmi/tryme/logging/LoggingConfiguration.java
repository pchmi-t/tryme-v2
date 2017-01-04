package org.fmi.tryme.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggingConfiguration {
	
	@Bean
	public LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}
}
