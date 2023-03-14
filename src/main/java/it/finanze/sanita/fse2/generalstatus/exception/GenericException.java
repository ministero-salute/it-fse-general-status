package it.finanze.sanita.fse2.generalstatus.exception;

import it.finanze.sanita.fse2.generalstatus.enums.ErrorClassEnum;
import lombok.Getter;

public class GenericException extends RuntimeException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 3796831298776564175L;

	@Getter
	private String type;

	@Getter
	private String title;

	@Getter
	private String detail;
	
	@Getter
	private String instance;


	public GenericException(ErrorClassEnum error, String inInstance,String inDetail, Exception e) {
		super(inDetail, e);
		type = error.getType();
		title = error.getTitle();
		detail = inDetail;
		instance = inInstance;
	}

	public GenericException(String msg, Exception e) {
		super(msg, e);
	}

	public GenericException(String msg) {
		super(msg, null);
	}

	public GenericException(Exception e) {
		super(null, e);
	}

}