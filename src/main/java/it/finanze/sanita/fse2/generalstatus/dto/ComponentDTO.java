package it.finanze.sanita.fse2.generalstatus.dto;

import lombok.Data;

@Data
public class ComponentDTO {

	MongoDTO mongo;
	
	KafkaDTO kafka;
}
