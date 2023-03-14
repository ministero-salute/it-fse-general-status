package it.finanze.sanita.fse2.generalstatus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusDetailDTO {

	private String name;
	private String uri;
	private LivenessCheckDTO detail;
}
