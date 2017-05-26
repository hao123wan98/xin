package com.zhenhr.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * PropertiesUtils
 * 
 * @author
 * 
 */
public class PropertiesUtils {
	/**
	 * 系统配置信息
	 * 
	 * @return
	 */
	public static Properties getWealthCacheProperties() {

		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("properties/memcached.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	/**
	 * weixin配置信息
	 * 
	 * @return
	 */
	public static Properties getWeixinProperties() {

		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("properties/weixin.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	/**
	 * weixin配置信息
	 * 
	 * @return
	 */
	public static Properties getStoreWeixinProperties() {

		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("properties/store_weixin.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	/**
	 * ali配置信息
	 * 
	 * @return
	 */
	public static Properties getAliPayProperties() {

		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("properties/alipay.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	/**
	 * 环信配置信息
	 * 
	 * @return
	 */
	public static Properties getZhenPinProperties() {

		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("properties/zhenhr.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	/**
	 * 短信配置信息
	 * 
	 * @return
	 */
	public static Properties getSmsTemplate() {

		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("properties/sms.properties"));

			// 转码处理
			Set<Object> keyset = prop.keySet();
			Iterator<Object> iter = keyset.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String newValue = null;
				try {
					// 属性配置文件自身的编码
					String propertiesFileEncode = "utf-8";
					newValue = new String(prop.getProperty(key).getBytes(
							"ISO-8859-1"), propertiesFileEncode);
				} catch (UnsupportedEncodingException e) {
					newValue = prop.getProperty(key);
				}
				prop.setProperty(key, newValue);
			}
		} catch (Exception e) {
		}

		return prop;
	}

	public static Properties getProperties(String name) {

		if (!name.contains(".")) {
			name += ".properties";
		}

		Properties p = new Properties();
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("test/" + name);

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
