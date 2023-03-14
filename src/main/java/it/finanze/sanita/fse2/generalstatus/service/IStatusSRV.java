package it.finanze.sanita.fse2.generalstatus.service;

import java.util.List;

import it.finanze.sanita.fse2.generalstatus.dto.StatusDetailDTO;

public interface IStatusSRV {

	List<StatusDetailDTO> status();
}
