package com.zhenhr.common;

public class BaseResultObj {
	private String code;
	private String msg;
	private String errorField;
	private String data;

	public BaseResultObj() {

	}

	public BaseResultObj(String errorCode, String msg) {
		this.setMsg(msg);
		this.setCode(errorCode);
	}

	public BaseResultObj(String errorCode, String errorField, String msg) {
		this.setMsg(msg);
		this.setErrorField(errorField);
		this.setCode(errorCode);
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

}
