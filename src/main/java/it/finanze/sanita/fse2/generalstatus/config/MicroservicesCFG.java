package it.finanze.sanita.fse2.generalstatus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MicroservicesCFG {

	@Bean
	@ConfigurationProperties(prefix = "ms")
	public BasicMsStatusInterceptor interceptor() {
		return new BasicMsStatusInterceptor();
	}

	public static class BasicMsStatusInterceptor {
		private final Map<String, String> status = new HashMap<>();
		public Map<String, String> getStatus() {
			return this.status;
		}
	}
}
