package it.finanze.sanita.fse2.generalstatus.exception;

import it.finanze.sanita.fse2.generalstatus.enums.ErrorClassEnum;

public class BusinessException extends GenericException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = -3596351690115285917L;
	

	public BusinessException(ErrorClassEnum error,String instance,String inDetail, Exception e) {
        super(error,instance, inDetail,e);
    }
	
	public BusinessException(String msg, Exception e) {
        super(msg, e);
    }

    public BusinessException(String msg) {
        super(msg, null);
    }

    public BusinessException(Exception e) {
        super(null, e);
    }
}
