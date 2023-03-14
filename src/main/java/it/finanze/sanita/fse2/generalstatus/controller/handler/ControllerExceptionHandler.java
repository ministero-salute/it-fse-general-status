/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.generalstatus.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import brave.Tracer;
import it.finanze.sanita.fse2.generalstatus.dto.ErrorResponseDTO;
import it.finanze.sanita.fse2.generalstatus.dto.LogTraceInfoDTO;
import it.finanze.sanita.fse2.generalstatus.enums.ErrorClassEnum;
import it.finanze.sanita.fse2.generalstatus.exception.BusinessException;
import it.finanze.sanita.fse2.generalstatus.exception.GenericException;
import it.finanze.sanita.fse2.generalstatus.utility.StringUtility;

/**
 *	Exceptions Handler.
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Tracker log.
	 */
	@Autowired
	private Tracer tracer;
	
	/**
	 * Management validation exception.
	 * 
	 * @param ex		exception
	 * @param request	request
	 * @return			
	 */
	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<ErrorResponseDTO> handleGenericException(final BusinessException ex, final WebRequest request) {
		ErrorResponseDTO dto = buildErrorResponseDTO(ex);
		return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	 
	}
	
	private LogTraceInfoDTO getLogTraceInfo() {
		return new LogTraceInfoDTO(
				tracer.currentSpan().context().spanIdString(), 
				tracer.currentSpan().context().traceIdString());
	}

	private ErrorResponseDTO buildErrorResponseDTO(final GenericException ex) {
		LogTraceInfoDTO infoDTO = getLogTraceInfo();
		
		ErrorClassEnum error = ErrorClassEnum.GENERIC;
		String type = error.getType();
		String title = error.getTitle();
		if(!StringUtility.isNullOrEmpty(ex.getType())){
			type = ex.getType();
		}
		
		if(!StringUtility.isNullOrEmpty(ex.getTitle())) {
			title = ex.getTitle();
		}
		
		String detail = ex.getDetail();
		String instance = ex.getInstance();
		return new ErrorResponseDTO(infoDTO, type, title, detail, instance);
	}
 
}