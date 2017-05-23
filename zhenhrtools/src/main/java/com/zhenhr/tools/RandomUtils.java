package com.zhenhr.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomUtils {
	public static final String ALLCHAR = "012356789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";
	public static final String NUMBERANDCHAR = "012356789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERANDLESSCHAR = "012356789abcdefghijklmnopqrstuvwxyz";
	public static final String WealthGiftCHAR = "012356789ABCDEFGHJKMNPQRSTUVWXYZ";

	/**
	 * 从数组中随机获取count个对象
	 * 
	 * @creator sgy
	 * @createTime:2015年3月1日 下午4:15:29 ==edit===========
	 * @updator:
	 * @updateTime 2015年3月1日 下午4:15:29
	 * @Description: 填写修改内容 ==============
	 * @param length
	 * @param count
	 * @return
	 */
	public static <T> List<T> random(List<T> src, int count) {
		List<T> toList = new ArrayList<T>();
		Random random = new Random();
		for (int i = 0; i < count && i < src.size(); i++) {
			int rno = random.nextInt(src.size());
			T tem = src.get(rno);
			toList.add(tem);
			src.remove(rno);
		}
		return toList;
	}

	/**
	 * 在0~maxNumber中随着获取count个不同的数字
	 * 
	 * @creator 李树涛
	 * @createTime:2015年3月1日 下午5:25:58 ==edit===========
	 * @updator: 李树涛
	 * @updateTime 2015年3月1日 下午5:25:58
	 * @Description: 填写修改内容 ==============
	 * @param maxNumber
	 * @param count
	 * @return
	 */
	public static List<Integer> random(int maxNumber, int count) {
		HashSet<Integer> hs = new HashSet<Integer>(); // hashset里面不允许有重复的值，如果有重复的值，是插不进去的（不会覆盖）
		Random r = new Random();
		if (maxNumber < count) {
			count = maxNumber;
		}

		while (hs.size() < count) {
			hs.add(r.nextInt(maxNumber));
		}
		return new ArrayList<Integer>(hs);
	}

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	public static String generateAllString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERANDCHAR.charAt(random.nextInt(NUMBERANDCHAR
					.length())));
		}
		return sb.toString();
	}

	public static String generateWealthGiftString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(WealthGiftCHAR.charAt(random.nextInt(WealthGiftCHAR
					.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(int num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 每次生成的len位数都不相同
	 * 
	 * @param param
	 * @return 定长的数字
	 */
	public static int getNotSimple(int[] param, int len) {
		Random rand = new Random();
		for (int i = param.length; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = param[index];
			param[index] = param[i - 1];
			param[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result * 10 + param[i];
		}
		return result;
	}

	public static String generateNumber(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(RandomUtils.ALLCHAR.length());

		System.out.println("generateString:" + generateString(6));
		System.out.println("generateMixString:" + generateMixString(6));
		System.out.println("generateLowerString:" + generateLowerString(6));
		System.out.println("generateUpperString:" + generateUpperString(10));
		System.out.println("generateZeroString:" + generateZeroString(10));
		System.out.println(toFixdLengthString(123, 10));
		System.out.println(toFixdLengthString(123L, 10));
		int[] in = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(getNotSimple(in, 3));
	}
}
