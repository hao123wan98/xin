package com.zhenhr.common;

public class ResultObj {
	private String code = TPErrorCodeGeneral.Succeed_Param;
	private Object data;
	private String msg = "ok";

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
