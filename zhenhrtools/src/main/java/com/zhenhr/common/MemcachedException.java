package com.zhenhr.common;

/**
 * 缓存异常
 * 
 * @author: 史国勇
 * @createTime: 2016年6月22日 下午9:12:32
 */
public class MemcachedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int type = 0; // 0:没有连接 1.数据操作错误
	public String key; // 操作类
	public String opt; // 操作方法
	public String className;//
	public String errorCode = TPErrorCodeGeneral.Error_Service;

	public MemcachedException() {

	}

	public MemcachedException(Class entityClass, String key, String opt) {
		this.className = entityClass.getName();
		this.key = key;
		this.opt = opt;
		type = 1;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

		buf.append("}");
		return buf.toString();
	}

}
