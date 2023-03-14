package it.finanze.sanita.fse2.generalstatus.client;

import it.finanze.sanita.fse2.generalstatus.dto.LivenessCheckDTO;

public interface IStatusClient {

	String callHealthReady(String name, String uri);
	
	LivenessCheckDTO callServerFhir(String name, String uri);
}
