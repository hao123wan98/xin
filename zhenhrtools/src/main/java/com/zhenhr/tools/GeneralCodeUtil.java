package com.zhenhr.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成唯一码
 * 
 * @author guoyongshi
 *
 */
public class GeneralCodeUtil {
	private static long tmpID = 0;
	private static boolean tmpIDlocked = false;

	public static synchronized String generateSequenceNo() {
		long ltime = 0;
		while (true) {
			if (tmpIDlocked == false) {
				tmpIDlocked = true;
				// 当前：（年、月、日、时、分、秒、毫秒
				ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS")
						.format(new Date()).toString());
				if (tmpID < ltime) {
					tmpID = ltime;
				} else {
					tmpID = tmpID + 1;
					ltime = tmpID;
				}
				tmpIDlocked = false;
				return String.valueOf(ltime);
			}
		}
	}

	public static void main(String[] args) {
		String no = GeneralCodeUtil.generateSequenceNo();
		Long tmp = Long.valueOf(no);
		System.out.println("" + tmp);
	}
}
