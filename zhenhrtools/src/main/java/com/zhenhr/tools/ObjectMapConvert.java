package com.zhenhr.tools;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapConvert {

	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			map.put(key, value);
		}

		return map;
	}

	/**
	 * 将map转对象
	 * 
	 * @param map
	 * @param classType
	 * @return
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> classType) {

		try {
			T obj = classType.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(classType);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				key = Underline2Camel.camel2Underline(key);
				if (map.containsKey(key)) {
					try {
						Object value = map.get(key);
						// 得到property对应的setter方法
						Method setter = property.getWriteMethod();
						if (!setter.getParameterTypes()[0].equals(value.getClass())) {
							if (value instanceof BigInteger) {
								value = ((BigInteger) value).longValue();
							}
						}
						setter.invoke(obj, value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

			return obj;
		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}
		return null;
	}

	public static <T> T mapToSuperObject(Map<String, Object> map, Class<T> classType) {

		try {
			T obj = classType.newInstance();

			// 自己
			BeanInfo beanInfo = Introspector.getBeanInfo(classType);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				key = Underline2Camel.camel2Underline(key);
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}
			copyValue(obj, map, classType.getSuperclass());

			return obj;
		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}
		return null;
	}

	private static <T> void copyValue(T obj, Map<String, Object> map,
			Class<T> classType) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(classType);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				key = Underline2Camel.camel2Underline(key);
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					try {
						setter.invoke(obj, value);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
