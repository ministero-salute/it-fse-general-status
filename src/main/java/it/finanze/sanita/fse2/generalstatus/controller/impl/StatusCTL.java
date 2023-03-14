package it.finanze.sanita.fse2.generalstatus.controller.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.finanze.sanita.fse2.generalstatus.controller.IStatusCTL;
import it.finanze.sanita.fse2.generalstatus.dto.StatusDTO;
import it.finanze.sanita.fse2.generalstatus.dto.StatusDetailDTO;
import it.finanze.sanita.fse2.generalstatus.service.IStatusSRV;

@RestController
public class StatusCTL implements IStatusCTL {

	@Autowired
	private IStatusSRV statusSRV;


	@Override
	public StatusDTO getEvents(HttpServletRequest request) {
		List<StatusDetailDTO> out = statusSRV.status();
		return new StatusDTO(out);
	}

}
