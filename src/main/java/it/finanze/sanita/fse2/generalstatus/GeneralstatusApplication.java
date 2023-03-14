package it.finanze.sanita.fse2.generalstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import it.finanze.sanita.fse2.generalstatus.client.impl.RestTemplateResponseErrorHandler;

@SpringBootApplication
public class GeneralstatusApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GeneralstatusApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
		rt.setErrorHandler(new RestTemplateResponseErrorHandler());
		return rt;
	}
	
}
