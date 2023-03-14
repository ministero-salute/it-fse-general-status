/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.generalstatus.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorClassEnum {

	/**
	 * Classe generica.
	 */
	GENERIC("/errors", "Errore Generico");
	
	/**
	 * Tipo classe errore.
	 */
	private String type;

	/**
	 * Descrizione classe errore.
	 */
	 private String title;
	 
	 public static ErrorClassEnum get(String type) {
			for (ErrorClassEnum v : ErrorClassEnum.values()) {
				if (v.name().equals(type)) {
					return v;
				}
			}
			return null;
		}

}
