package it.finanze.sanita.fse2.generalstatus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.finanze.sanita.fse2.generalstatus.dto.StatusDTO;

@RequestMapping(path = "/v1")
@Tag(name = "Servizio status")
public interface IStatusCTL {


	@GetMapping(value = "/check-general-status")
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	@Operation(summary = "General status", description = "General status.")
	StatusDTO getEvents(HttpServletRequest request);

}
