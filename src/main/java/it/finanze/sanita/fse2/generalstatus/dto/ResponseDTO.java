/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.generalstatus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base response.
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO {

	/**
	 * Trace id log.
	 */
	@Schema(description = "Indentificativo univoco della richiesta dell'utente")
	private String traceID;

	/**
	 * Span id log.
	 */
	@Schema(description = "Indentificativo univoco di un task della richiesta dell'utente (differisce dal traceID solo in caso di chiamate sincrone in cascata)")
	private String spanID;

	/**
	 * Instantiates a new response DTO.
	 */
	public ResponseDTO(final LogTraceInfoDTO traceInfo) {
		traceID = traceInfo.getTraceID();
		spanID = traceInfo.getSpanID();
	}

}
