package it.finanze.sanita.fse2.generalstatus.client.impl;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import it.finanze.sanita.fse2.generalstatus.client.IStatusClient;
import it.finanze.sanita.fse2.generalstatus.dto.LivenessCheckDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StatusClient implements IStatusClient {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String callHealthReady(final String name, final String uri) {
		String out = "";
		log.info(String.format("%s - call status ms: %s", name, uri));
		String url = uri + "/health-ready";
		try {
			out = restTemplate.getForObject(url, String.class);
		} catch(ResourceAccessException ex) {
			log.error(""+ex);
			throw ex;
		} 
		return out; 
	}

	@Override
	public LivenessCheckDTO callServerFhir(final String name, final String uri) {
		LivenessCheckDTO out = new LivenessCheckDTO();
		out.setStatus(Health.down().build().getStatus().getCode());
		log.info(String.format("%s - call status ms: %s", name, uri));
		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			boolean isUp = (responseCode >= 200 && responseCode < 300);
			if(isUp) {
				out.setStatus(Health.up().build().getStatus().getCode());
			}
		} catch (Exception e) {
			out.setStatus(Health.down().build().getStatus().getCode());
		}
		return out;

	}

}
