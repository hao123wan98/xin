package com.zhenhr.common;

public class ParameterException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String errorCode = TPErrorCodeGeneral.Error_Param;

	public ParameterException(String message, String errorCode) {
		super(message);
	}

	public ParameterException(String message) {
		super(message);
	}
}
