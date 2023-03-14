package it.finanze.sanita.fse2.generalstatus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivenessCheckDTO {

	 private String status;
	 
	 private ComponentDTO components;
	 
}
