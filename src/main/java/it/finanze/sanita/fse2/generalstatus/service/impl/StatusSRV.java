package it.finanze.sanita.fse2.generalstatus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.finanze.sanita.fse2.generalstatus.client.IStatusClient;
import it.finanze.sanita.fse2.generalstatus.config.MicroservicesCFG.BasicMsStatusInterceptor;
import it.finanze.sanita.fse2.generalstatus.dto.ComponentDTO;
import it.finanze.sanita.fse2.generalstatus.dto.KafkaDTO;
import it.finanze.sanita.fse2.generalstatus.dto.LivenessCheckDTO;
import it.finanze.sanita.fse2.generalstatus.dto.MongoDTO;
import it.finanze.sanita.fse2.generalstatus.dto.StatusDetailDTO;
import it.finanze.sanita.fse2.generalstatus.enums.ErrorClassEnum;
import it.finanze.sanita.fse2.generalstatus.exception.BusinessException;
import it.finanze.sanita.fse2.generalstatus.service.IStatusSRV;

@Service
public class StatusSRV implements IStatusSRV {

	@Autowired
	private IStatusClient statusClient;

	@Autowired
	private BasicMsStatusInterceptor status;

	@Override
	public List<StatusDetailDTO> status() {
		List<StatusDetailDTO> out = new ArrayList<>();
		for(Entry<String, String> ms : status.getStatus().entrySet()) {
			try {
				if("datarepo-fhir-server".equals(ms.getKey())) {
					LivenessCheckDTO statusServerFhir = statusClient.callServerFhir(ms.getKey(), ms.getValue());
					out.add(new StatusDetailDTO(ms.getKey(), ms.getValue(), statusServerFhir));
				} else {
					String json = statusClient.callHealthReady(ms.getKey(), ms.getValue());
					out.add(new StatusDetailDTO(ms.getKey(), ms.getValue(), buildLivenessCheckDTO(json)));
				}
			} catch(ResourceAccessException ex) {
				out.add(new StatusDetailDTO(ms.getKey(), ms.getValue(), LivenessCheckDTO.builder().status(Health.down().build().getStatus().getCode()).build()));
			}
		}
		return out;
	}


	private LivenessCheckDTO buildLivenessCheckDTO(final String json) {
		LivenessCheckDTO out = new LivenessCheckDTO();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(json);

			JsonNode generalNode = root.path("components").path("livenessCheckController");
			if(generalNode.isMissingNode()) {
				generalNode = root.path("components").path("livenessCheckCTL");
			}

			String generalStatus = "DOWN";
			if(!generalNode.isMissingNode()) {
				generalStatus = generalNode.get("status").asText(); 
			}

			out.setStatus(generalStatus);
			JsonNode mongoNode = root.path("components").path("mongo");
			ComponentDTO comp = new ComponentDTO();
			if(!mongoNode.isMissingNode()) {
				String status = mongoNode.get("status").asText();
				comp.setMongo(new MongoDTO(status));
				out.setComponents(comp);
			}

			JsonNode kafkaNode = root.path("components").path("kafka");
			if(!kafkaNode.isMissingNode()) {
				String status = kafkaNode.get("status").asText();
				comp.setKafka(new KafkaDTO(status));
				out.setComponents(comp);
			}
		} catch(Exception ex) {
			throw new BusinessException(ErrorClassEnum.GENERIC, "instance", "detail", ex);
		}
		return out;
	}
}
