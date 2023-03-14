/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.generalstatus.client.impl;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return false;
	}

}
