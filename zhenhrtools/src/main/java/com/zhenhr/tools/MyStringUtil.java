package com.zhenhr.tools;

public class MyStringUtil {

	public static int toIntValue(String value) {
		if (value == null || "".equals(value)) {
			return 0;
		}

		int ret = 0;
		try {
			ret = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static int toIntValue(String value, int defaultValue) {
		if (value == null || "".equals(value)) {
			return defaultValue;
		}

		int ret = defaultValue;
		try {
			ret = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static float toFloatValue(String value) {
		if (value == null || "".equals(value)) {
			return 0;
		}

		float ret = 0;
		try {
			ret = Float.valueOf(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 分割字符串通过数字
	 * 
	 * @param str
	 * @return
	 */
	public static String[] splitStringWithNumber(String str) {
		if (str == null) {
			return null;
		}

		int index = str.length() - 1;
		for (int i = str.length(); --i >= 0;) {
			char ch = str.charAt(i);
			if (!Character.isDigit(ch)) {
				break;
			} else {
				index = i;
			}
		}

		String[] result = new String[2];
		// 0 字符 1数字
		if (index <= str.length() - 1) {
			result[0] = str.substring(0, index);
			result[1] = str.substring(index);
		} else {
			result[0] = str;
			result[1] = "";
		}
		return result;
	}

	public static boolean isStringContrainNumber(String str) {
		if (str == null) {
			return false;
		}

		for (int i = str.length(); --i >= 0;) {
			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				return true;
			}
		}
		return false;
	}
}
