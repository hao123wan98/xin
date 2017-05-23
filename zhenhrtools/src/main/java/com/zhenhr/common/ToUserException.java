package com.zhenhr.common;

public class ToUserException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String errorCode = TPErrorCodeGeneral.Error_ToUser;
	public String errorField = null;

	public ToUserException(String message, String code, String errorField) {
		super(message);
		this.errorCode = code;
		this.errorField = errorField;
	}

	public ToUserException(String message, String errorField) {
		super(message);
		this.errorField = errorField;
	}

}
