package com.zhenhr.tools;

import java.util.Date;

/**
 * 社保工具
 * 
 * @author guoyongshi
 *
 */
public class InsuranceUtil {

	public static boolean isEndMonth(String dt, String endDt) {
		String[] tmps = endDt.split("-");
		int year = Integer.valueOf(tmps[0]);
		int month = Integer.valueOf(tmps[1]);

		String[] tmps1 = dt.split("-");
		int year1 = Integer.valueOf(tmps1[0]);
		int month1 = Integer.valueOf(tmps1[1]);
		if (year1 > year) {
			return true;
		}

		if (year1 == year) {
			if (month1 > month) {
				return true;
			}
		}

		return false;
	}

	public static boolean isEndMonth(String endDt) {
		String dt = DateUtil.yearMonthStrWithSep(new Date());
		return isEndMonth(dt, endDt);
	}

	public static boolean isStartMonth(String curDate, String startDate) {
		String[] tmps = startDate.split("-");
		int year = Integer.valueOf(tmps[0]);
		int month = Integer.valueOf(tmps[1]);

		String[] tmps1 = curDate.split("-");
		int year1 = Integer.valueOf(tmps1[0]);
		int month1 = Integer.valueOf(tmps1[1]);
		if (year < year1) {
			return true;
		}

		if (year1 == year) {
			if (month < month1) {
				return true;
			}
		}

		return false;
	}
}
