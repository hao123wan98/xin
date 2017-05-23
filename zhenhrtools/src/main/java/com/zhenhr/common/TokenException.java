package com.zhenhr.common;

public class TokenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String errorCode = TPErrorCodeGeneral.Error_TokenError;

	public TokenException(String message, String code) {
		super(message);
		this.errorCode = code;
	}

	public TokenException(String message) {
		super(message);
	}
}
