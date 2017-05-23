package com.zhenhr.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.zhenhr.common.ParameterException;

/**
 * 对象管理
 * 
 * @author guoyongshi
 * 
 */
public class ObjectUtils {

	/**
	 * 将origin 非空属性复制到 destination
	 * 
	 * @param origin
	 * @param destination
	 */
	public static <T> void mergeSameClassValue(T origin, T destination) {
		if (origin == null || destination == null)
			return;
		if (!origin.getClass().equals(destination.getClass())) {
			throw new ParameterException("error class");
		}

		Field[] fields = origin.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[i];
				field.setAccessible(true);
				Object value = field.get(origin);
				if (null != value) {
					field.set(destination, value);
				}
				field.setAccessible(false);
			} catch (Exception e) {
			}
		}
	}

	public static <T> void mergeDifferClassValue(T origin, T destination) {
		if (origin == null || destination == null)
			return;

		Field[] fields = origin.getClass().getDeclaredFields();
		Field[] fields1 = null;
		if (destination instanceof Class) {
			fields1 = ((Class<?>) destination).getDeclaredFields();
		} else if (destination instanceof Object) {
			fields1 = destination.getClass().getDeclaredFields();
		}

		if (fields1 == null) {
			return;
		}

		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[i];
				field.setAccessible(true);
				Object value = field.get(origin);
				if (null != value) {
					for (int m = 0; m < fields1.length; m++) {
						Field field1 = fields1[m];
						if (field1.getName().equals(field.getName())) {
							field1.setAccessible(true);
							field1.set(destination, value);
							field1.setAccessible(false);
							break;
						}
					}
				}
				field.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		mergeDifferClassValueSuper(origin, destination,
				destination.getClass().getSuperclass());

	}
	public static <T> void mergeDifferClassValueSuper(T origin, T destination,
			Class<?> parentClass) {
		if (parentClass == null || parentClass.equals(Object.class)) {
			return;
		}

		Field[] fields = origin.getClass().getDeclaredFields();
		Field[] fields1 = parentClass.getDeclaredFields();

		if (fields1 == null) {
			return;
		}

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			try {
				field.setAccessible(true);
				Object value = field.get(origin);
				if (null != value) {
					for (int m = 0; m < fields1.length; m++) {
						Field field1 = fields1[m];
						if (field1.getName().equals(field.getName())) {
							field1.setAccessible(true);
							field1.set(destination, value);
							field1.setAccessible(false);
							break;
						}
					}
				}
				field.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		mergeDifferClassValueSuper(origin, destination, parentClass.getSuperclass());
	}

	/**
	 * 对象为null的数字属性设置为0
	 * 
	 * @param obj
	 */
	public static <T> void setZeroWithNull(T obj) {
		if (obj == null)
			return;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			if (fd.getName().equals("tid")) {
				continue;
			}

			try {
				fd.setAccessible(true);
				if (fd.get(obj) == null) {
					if (fd.getType().equals(Integer.class)) {
						Integer value = 0;
						fd.set(obj, value);
					} else if (fd.getType().equals(Long.class)) {
						Long value = 0l;
						fd.set(obj, value);
					} else if (fd.getType().equals(Float.class)) {
						Float value = 0.0f;
						fd.set(obj, value);
					}
				}
				fd.setAccessible(false);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static <T> void setAllZeroWithNull(T obj) {
		if (obj == null)
			return;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			if (fd.getName().equals("tid")) {
				continue;
			}

			try {
				fd.setAccessible(true);
				if (fd.get(obj) == null) {
					if (fd.getType().equals(Integer.class)) {
						Integer value = 0;
						fd.set(obj, value);
					} else if (fd.getType().equals(Long.class)) {
						Long value = 0l;
						fd.set(obj, value);
					} else if (fd.getType().equals(Float.class)) {
						Float value = 0.0f;
						fd.set(obj, value);
					} else if (fd.getType().equals(String.class)) {
						fd.set(obj, "");
					} else if (fd.getType().equals(Boolean.class)) {
						Boolean value = false;
						fd.set(obj, value);
					}
				}
				fd.setAccessible(false);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 将父类所有的属性COPY到子类中。 类定义中child一定要extends father；
	 * 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate
	 */
	public static void fatherToChild(Object father, Object child) {
		if (!(child.getClass().getSuperclass() == father.getClass())) {
			System.err.println("child不是father的子类");
		}

		Field[] fields = father.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[i];
				field.setAccessible(true);
				Object value = field.get(father);
				if (null != value) {
					field.set(child, value);
				}
				field.setAccessible(false);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 获取非空的field
	 * 
	 * @param obj
	 */
	public static List<String> getNotNullFieldName(Object obj) {
		List<String> list = new ArrayList<String>();

		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			try {
				fd.setAccessible(true);
				if (fd.get(obj) != null) {
					list.add(fd.getName());
				}
				fd.setAccessible(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 获取一个对象的属性
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldByName(Object obj, String fieldName) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			if (fd.getName().endsWith(fieldName)) {
				try {
					fd.setAccessible(true);
					Object value = fd.get(obj);
					fd.setAccessible(false);
					return value;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static <T> void setValueByFieldName(T obj, String fieldName, Object value) {
		if (obj == null)
			return;

		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			if (fd.getName().equals(fieldName)) {
				try {
					fd.setAccessible(true);
					if (fd.getType().equals(value.getClass())) {
						fd.set(obj, value);
					} else {
						String tmp = String.valueOf(value);
						if (fd.getType().equals(Long.class)) {
							fd.set(obj, Long.valueOf(tmp));
						} else if (fd.getType().equals(Float.class)) {
							fd.set(obj, Float.valueOf(tmp));
						} else if (fd.getType().equals(String.class)) {
							fd.set(obj, tmp);
						} else if (fd.getType().equals(Boolean.class)) {
							if ("1".equals(tmp)) {
								fd.set(obj, true);
							} else {
								fd.set(obj, false);
							}
						}

					}
					fd.setAccessible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
	}

}
