package com.zhenhr.common;

import com.zhenhr.tools.JsonUtil;



/**
 * 无效数据产生的异常
 * 
 */
public class ServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String className;
	public Long id;
	public String key;
	public String opt;
	public String dataJson;

	public String errorCode = TPErrorCodeGeneral.Error_Service;

	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param entityClass
	 *            异常数据类
	 * @param id
	 *            数据id
	 */
	public ServiceException(Class entityClass, Long id, Object data) {
		this.className = entityClass.getName();
		this.id = id;
		this.dataJson = JsonUtil.toJson(data);
	}

	public ServiceException(Class entityClass, String key, String opt,
			Object data) {
		this.className = entityClass.getName();
		this.key = key;
		this.opt = opt;
		this.dataJson = JsonUtil.toJson(data);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public String getLogInfo() {
		StringBuffer buf = new StringBuffer("{");
		if (this.getClassName() != null) {
			buf.append("className:" + this.getClassName() + ",");
		}

		if (this.getKey() != null) {
			buf.append("key:" + this.getKey() + ",");
		}

		if (this.getOpt() != null) {
			buf.append("opt:" + this.getOpt() + ",");
		}
		buf.append("id:" + this.getId() + ",");
		buf.append("dataJson:" + this.getDataJson());
		buf.append("}");
		return buf.toString();
	}
}
