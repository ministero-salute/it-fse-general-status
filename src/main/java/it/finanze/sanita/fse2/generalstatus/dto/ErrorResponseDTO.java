/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.generalstatus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Error response
 */
@Data
public class ErrorResponseDTO {

    /**
     * Trace id log.
     */
    @Schema(description = "Identificativo univoco della richiesta dell'utente")
    private String traceID;

    /**
     * Span id log.
     */
    @Schema(description = "Identificativo univoco di un task della richiesta dell'utente (differisce dal traceID solo in caso di chiamate sincrone in cascata)")
    private String spanID;

    /**
     * Issue identifier
     */
    @Schema(description = "Identificativo del problema verificatosi")
    private String type;

    /**
     * Issue summary
     */
    @Schema(description = "Sintesi del problema (invariante per occorrenze diverse dello stesso problema)")
    private String title;

    /**
     * Issue description
     */
    @Schema(description = "Descrizione del problema")
    private String detail;

    /**
     * Additional information
     */
    @Schema(description = "URI che potrebbe fornire ulteriori informazioni riguardo l'occorrenza del problema")
    private String instance;

    public ErrorResponseDTO(final LogTraceInfoDTO traceInfo, final String inType, final String inTitle, final String inDetail,final String inInstance) {
        traceID = traceInfo.getTraceID();
        spanID = traceInfo.getSpanID();
        type = inType;
        title = inTitle;
        detail = inDetail;
        instance = inInstance;
    }

}

