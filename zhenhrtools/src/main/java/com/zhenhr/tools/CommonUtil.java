package com.zhenhr.tools;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		boolean result = true;
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			result = (obj.toString().trim().length() == 0)
					|| obj.toString().trim().equals("null");
		} else if (obj instanceof Collection) {
			result = ((Collection) obj).size() == 0;
		} else {
			result = ((obj == null) || (obj.toString().trim().length() < 1)) ? true
					: false;
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public static Integer getCreateTimeWithSecond() {
		Date dt = new Date();
		return (int) (dt.getTime() / 1000);
	}
	
	/**
	 * 根据filename ext 获取KEY
	 * @return
	 */
	public static String productFileKey(String filename,String ext){
		
		String name = "";
		String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		if(filename == null || "".equals(filename.trim())){
			name = UUID.randomUUID().toString().replace("-", "");
		}else{
			name = filename ;
		} 
		
		if(ext == null || "".equals(ext.trim())){
			return new StringBuffer().append(date).append("/").append(name).toString();
		}else{
			return new StringBuffer().append(date).append("/").append(name).append(".").append(ext).toString();
		}
	}
	
	
}
