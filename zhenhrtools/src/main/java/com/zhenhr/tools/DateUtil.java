package com.zhenhr.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {

    public static final String[] zodiacArr = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙",
            "蛇", "马", "羊"};

    public static final String[] constellationArr = {"水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    public static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23,
            23, 22, 22};

    /**
     * 1分钟
     */
    public static final int OneMinitueInSecond = 60;

    /**
     * 5分钟
     */
    public static final int FiveMinitueInSecond = 300;

    /**
     * 十分钟
     */
    public static final int TenMinitueInSecond = 600;

    /**
     * 一小时用秒
     */
    public static final int OneHourInSecond = 3600;

    /**
     * 一天用秒
     */
    public static final int OneDayInSecond = 3600 * 24;

    /**
     * 一礼拜用秒
     */
    public static final int OneWeekInSecond = 3600 * 24 * 7;

    /**
     * 一个月用秒
     */
    public static final int OneMonthInSecond = 3600 * 24 * 30;

    public static Date dateOfToday() {
        String dt = DateUtil.date2StringyyyyMMdd(new Date());
        return DateUtil.string2DateyyyyMMdd(dt);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static String date2Zodica(Calendar time) {
        return zodiacArr[time.get(Calendar.YEAR) % 12];
    }

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static List<String> getNowWeekDay(String date) {
        // 定义输出日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        List<Date> days = dateToWeek(currentDate);
        List<String> reslut = new ArrayList<>();
        for (Date d : days) {
            reslut.add(sdf.format(d));
        }
        return reslut;
    }

    public static String getThisWeekFirstDay(String date) {
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        //所在周开始日期
        //System.out.println();
        String first = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        //所在周结束日期
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        return first;
    }


    /**
     * 根据日期获得所在周的日期
     *
     * @param mdate
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a - 1, fdate);
        }
        return list;
    }

    public static Date getThisWeekLasttDay(String date) {
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        //所在周开始日期
        //System.out.println();
        //String first = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        //所在周结束日期
        //System.out.println();
        return cal.getTime();
    }

    public static String date2Constellation(Calendar time) {
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(Date d) {
        Calendar time = Calendar.getInstance();
        time.setTime(d);
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }

    // Second
    // Millisecond

    public static int date2Second(Date d) {
        return (int) (d.getTime() / 1000);
    }

    public static String date2SecondStr(Date d) {
        return String.valueOf((d.getTime() / 1000));
    }

    /**
     * 缓存的秒
     *
     * @param d
     * @return
     */
    public static int getCacheSecond(Date endDt) {
        return (int) ((endDt.getTime() - new Date().getTime()) / 1000);
    }

    /**
     * 获取int时间转换String类型
     *
     * @param second
     * @return
     */
    public static String second2StringyyyyMMdd(int second) {
        Long op = (long) second;
        Long timestamp = op * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp));
        return date;
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @Title: getTimeMillisecond @Description: @param @return 设定文件 @return Long
     * 返回类型 @throws
     */
    public static Long second2milli() {
        return new Date().getTime();
    }

    /**
     * 获取毫秒数
     *
     * @Title: getTimeMillisecond @Description: @param @param
     * second @param @return 设定文件 @return Long 返回类型 @throws
     */
    public static Long second2milli(int second) {
        return second * 1000L;
    }

    /**
     * 获取当前时间的秒数
     *
     * @return int 返回类型 @throws
     */
    public static int second() {
        return (int) (new Date().getTime() / 1000);
    }

    /**
     * 获取秒数
     *
     * @Title: getTimeSecond
     */
    public static int getTimeSecond(Long millisecond) {
        return (int) (millisecond / 1000);
    }

    /**
     * 将yyyyMMdd 格式的时间转化为 date 类型数据返回
     *
     * @param str
     * @return
     */
    @SuppressWarnings("finally")
    public static Date string2DateyyyyMMdd(String str) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {

            e.printStackTrace();
        } finally {
            return d;
        }
    }

    public static Date string2DateyyyyMM(String str) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    @SuppressWarnings("finally")
    public static Date string2DateTime(String str) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {

            e.printStackTrace();
        } finally {
            return d;
        }
    }

    public static Date string2Date(String str) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {

            e.printStackTrace();
        } finally {
            return d;
        }
    }

    public static String date2StringyyyyMM(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(d);
    }

    public static String date2StringyyyyMMdd(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static String date2FullStr(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return sdf.format(d);
    }

    /**
     * @param offsetDate
     * @return
     * @title getOffsetDate000000
     * @description 为0时为当前日期，为正数时为当前日期的后N天，为负数时为前N天
     */
    public static Date getOffsetDate000000(int offsetDate) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (offsetDate != 0) {
            cal.add(Calendar.DATE, offsetDate);
        }
        return cal.getTime();
    }

    /**
     * @param date
     * @param offsetDate
     * @return
     * @title getOffsetDate000000
     * @description 为0时为当前date，为正数时为当前date的后N天，为负数时为前N天
     */
    public static Date getOffsetDate000000(Date date, int offsetDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (offsetDate != 0) {
            cal.add(Calendar.DATE, offsetDate);
        }
        return cal.getTime();
    }

    public static String getOffsetDate000000(String dateStr, int offsetDate) {
        Date dt = DateUtil.string2Date(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (offsetDate != 0) {
            cal.add(Calendar.DATE, offsetDate);
        }

        return DateUtil.date2FullStr(cal.getTime());
    }

    /**
     * 获得每日本年第几周
     *
     * @return
     */
    public static int yearWeek() {
        // TimeZone.setDefault（TimeZone.getTimeZone（"Asia/Shanghai"））；
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        // calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        int year2 = calendar.get(Calendar.YEAR);
        if (week < calendar.get(Calendar.WEEK_OF_YEAR)) {
            year2 += 1;
        }
        int year = 0;
        if (year2 < year1) {
            year = year1;
        } else {
            year = year2;
        }
        return year * 100 + week;
    }

    /**
     * 获得每日本年第几周
     *
     * @return
     */
    public static int yearAndweek(Date date) {
        // TimeZone.setDefault（TimeZone.getTimeZone（"Asia/Shanghai"））；
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        // calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        int year2 = calendar.get(Calendar.YEAR);
        if (week < calendar.get(Calendar.WEEK_OF_YEAR)) {
            year2 += 1;
        }
        int year = 0;
        if (year2 < year1) {
            year = year1;
        } else {
            year = year2;
        }
        return year * 100 + week;
    }

    /**
     * 获得每日本年第几周下周
     *
     * @return
     */
    public static int yearNextWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());

        calendar.add(Calendar.WEEK_OF_YEAR, +1);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.add(Calendar.DAY_OF_MONTH, -7 * 2);
        int year = calendar.get(Calendar.YEAR);
        if (week < calendar.get(Calendar.WEEK_OF_YEAR)) {
            year += 1;
        }
        return year * 100 + week;
    }

    public static int yearWeekMove(int moveCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());

        calendar.add(Calendar.WEEK_OF_YEAR, -moveCount);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.add(Calendar.DAY_OF_MONTH, -7 * 2);
        int year = calendar.get(Calendar.YEAR);
        if (week < calendar.get(Calendar.WEEK_OF_YEAR)) {
            year += 1;
        }
        return year * 100 + week;
    }

    /**
     * 获得每日本年第几周上周
     *
     * @return
     */
    public static int yearUpWeek() {
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());

        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        calendar.add(Calendar.DAY_OF_MONTH, -7);
        int year2 = calendar.get(Calendar.YEAR);
        if (week < calendar.get(Calendar.WEEK_OF_YEAR)) {
            year2 += 1;
        }
        int year = 0;
        if (year2 < year1) {
            year = year1;
        } else {
            year = year2;
        }
        return year * 100 + week;
    }

    public static int year() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static int year(String yearMonthStr) {
        String[] list = yearMonthStr.split("-");
        if (list.length < 2) {
            return 0;
        }
        int year = Integer.valueOf(list[0]);
        return year;
    }

    public static int month() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int month(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int day(String dt) {
        if (dt == null) {
            return 0;
        }
        String[] tmps = dt.split("-");
        if (tmps.length == 3) {
            return Integer.valueOf(tmps[2]);
        }
        return 0;
    }

    public static int yearMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    public static int yearMonth(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    public static int yearMonth(String yearMonthStr) {
        String[] list = yearMonthStr.split("-");
        if (list.length < 2) {
            return 0;
        }
        int year = Integer.valueOf(list[0]);
        int month = Integer.valueOf(list[1]);
        return year * 100 + month;
    }

    public static String yearMonthStr() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "" + year + "0" + month;
        } else {
            return "" + year + month;
        }
    }

    public static String yearMonthStrWithSep(String dtStr) {
        Date dt = string2DateyyyyMMdd(dtStr);
        return yearMonthStrWithSep(dt);
    }

    public static String yearMonthStrWithSep(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "" + year + "-0" + month;
        } else {
            return "" + year + "-" + month;
        }
    }

    public static String yearMonthDayStr() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = "" + month;
        }
        String dayStr;
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = "" + day;
        }
        return "" + year + monthStr + dayStr;
    }

    public static int yearMonthMove(Integer moveCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -moveCount);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    public static int yearMonthMove(Date date, Integer moveCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, -moveCount);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    /**
     * 获取加减分钟
     *
     * @return
     */
    public static Date getaddsubtMinute(Date d, int minute) {
        Calendar calendar = Calendar.getInstance();// 日历对象
        try {
            calendar.setTime(d);
            // 设置当前日期
            calendar.add(Calendar.MINUTE, -minute);// 分钟减一
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从现在到后面第几天的0点终止 days 是不算今天需要加的天数，比如到明天12点终止 传1
     *
     * @throws ParseException
     */
    public static Date toAddDayEndTime(int days) throws ParseException {
        Calendar calendar = Calendar.getInstance();// 日历对象
        calendar.setTime(new Date());
        int curday = calendar.get(Calendar.DAY_OF_YEAR);
        curday += days + 1;
        calendar.set(Calendar.DAY_OF_YEAR, curday);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String tmp = sdf.format(calendar.getTime());
        return sdf.parse(tmp);

    }

    /**
     * 从现在到后面第几天的0点终止 days 是不算今天需要加的天数，比如到明天12点终止 传1
     *
     * @throws ParseException
     */

    public static String calcPreDates(int preDays) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date();
            String cyTime = sdf.format(date.getTime() - preDays * 24 * 60 * 60 * 1000);
            return cyTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加小时
     *
     * @param amount
     * @return
     */
    public static Date addHour(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, amount);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    /**
     * 给指定日期添加或减去指定天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public static int daysOfTwoDate(Date beginDate, Date endDate) {
        int days = Math
                .round((endDate.getTime() - beginDate.getTime()) / 1000 / 60 / 60 / 24);
        return days;
    }

    //获取当前时间是周几
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 给指定日期添加或减去指定月
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonthToFirstDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    public static Date addMonthToSameDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
        int maxDay1 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, amount);
        int maxDay2 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (maxDay2 <= day1 || maxDay1 == day1) {
            calendar.set(Calendar.DAY_OF_MONTH, maxDay2);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, day1);
        }

        return calendar.getTime();
    }

    /**
     * @param
     * @return Timestamp
     * @title getCurrentTimestamp
     * @author
     * @description 获得当前的timestamp，频繁地使用new Timestamp(new
     * Date().getTime())方法获得当前的timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 剩余天数
     *
     * @param end
     * @return
     */
    public static int remainDays(long endtime) {

        long days = (endtime - new Date().getTime()) / (1000 * 3600 * 24) + 1;
        return (int) days;
    }

    public static String getFirstDayOfLastMonth1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date nowdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowdate);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        String str = sdf.format(c.getTime());
        return str;
    }

    public static String getFirstDayOfCurMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date nowdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowdate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        String str = sdf.format(c.getTime());
        return str;
    }

    public static String getFirstDayOfNextMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        String str = sdf.format(c.getTime());
        return str;
    }

    public static String getLastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        Date nowdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowdate);
        c.add(Calendar.MONTH, -1);
        String str = sdf.format(c.getTime());
        return str;
    }

    public static Date getNextMonthLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, +1);

        int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);
        return cal.getTime();
    }

    public static String addMouth(String nowDate, int mouth) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(string2DateyyyyMMdd(nowDate));

        calendar.add(Calendar.MONTH, mouth);
        String str = date2StringyyyyMMdd(calendar.getTime());
        return str;
    }

    ;

    /**
     * 获取当月的最后一天
     *
     * @param dt
     * @return
     */
    public static Date getMonthLastDay(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);
        return cal.getTime();
    }

    public static int getMonthLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 0);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    public static String getDateOfFirstDay(String changeDt) {
        String[] tmps = changeDt.split("-");
        String firstDt = tmps[0] + "-" + tmps[1] + "-01";
        return firstDt;
    }

    public static String getDateOfLastDay(String changeDt) {
        String[] tmps = changeDt.split("-");
        int year = Integer.valueOf(tmps[0]);
        int month = Integer.valueOf(tmps[1]);
        String endDt = tmps[0] + "-" + tmps[1] + "-"
                + DateUtil.getMonthLastDay(year, month);
        return endDt;
    }

    public static Timestamp getBeginOfCurrentMonth() {
        Date nowdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowdate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Timestamp ts = new java.sql.Timestamp(c.getTime().getTime());
        return ts;
    }

    /**
     * 获取某一段时间的每一天日期
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> findDates(String start, Date end) {
        // String start = "2014-10-01";
        // String end = "2014-11-04";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin;
        List<String> list = new ArrayList<String>();
        try {
            dBegin = sdf.parse(start);
            List<Date> lDate = handleDates(dBegin, end);
            for (Date date : lDate) {
                list.add(sdf.format(date));
            }
            return list;
        } catch (ParseException e) {
            return null;
        }
    }

    private static List<Date> handleDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            lDate.add(calBegin.getTime());
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
        }
        return lDate;
    }

    /**
     * 取年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static String getAge(String birthDay) {
        if (birthDay == null || birthDay.isEmpty()) {
            return "";
        }

        Calendar cal = Calendar.getInstance();
        ;
        Date dt = null;
        try {
            dt = string2DateyyyyMMdd(birthDay);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        if (dt == null) {
            return "";
        }

        if (cal.before(birthDay)) {
            return "0";
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(dt);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }

        return age + "";
    }

    /**
     * 一个月有多少个工作日
     *
     * @param dt
     * @return
     */
    public static int workDaysInMonth(String yearMonthStr) {
        String[] list = yearMonthStr.split("-");
        if (list.length < 2) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(list[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(list[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stdate = calendar.getTime();
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);

        Date endDate = calendar.getTime();

        return workDays(stdate, endDate);
    }

    /**
     * 从月初到这天工作了几天
     *
     * @param dt
     * @return
     */
    public static int workDaysFromMonthFirstDay(String dtStr) {
        Date dt = string2DateyyyyMMdd(dtStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stdate = calendar.getTime();
        return workDays(stdate, dt);
    }

    /**
     * 到月底有多少个工作日
     *
     * @param dt
     * @return
     */
    public static int workDaysToMonthEndDay(String dtStr) {
        if (dtStr == null) {
            return 0;
        }
        Date dt = string2DateyyyyMMdd(dtStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        Date enddate = calendar.getTime();
        return workDays(dt, enddate);
    }

    public static int workDays(String dt1, String dt2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int rs = 0;

        try {
            Date begDate = sdf.parse(dt1);
            Date endDate = sdf.parse(dt2);
            if (begDate.after(endDate)) {
                return 0;
            }

            rs = workDays(begDate, endDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rs;
    }

    //判断两个时间 相差几天
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static int week(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.string2DateyyyyMMdd(dateStr));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isWorkDay(String dateStr) {
        int week = week(dateStr);
        if (week == 7 || week == 1) {
            return false;
        }
        return true;
    }

    public static int workDays(Date begDate, Date endDate) {
        int rs = 0;

        // 总天数
        int days = (int) ((endDate.getTime() - begDate.getTime()) / (24 * 60 * 60 * 1000))
                + 1;
        // 总周数，s
        int weeks = days / 7;
        // 整数周
        if (days % 7 == 0) {
            rs = days - 2 * weeks;
        } else {
            Calendar begCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            begCalendar.setTime(begDate);
            endCalendar.setTime(endDate);
            // 周日为1，周六为7
            int beg = begCalendar.get(Calendar.DAY_OF_WEEK);
            int end = endCalendar.get(Calendar.DAY_OF_WEEK);
            if (beg > end) {
                rs = days - 2 * (weeks + 1);
            } else if (beg < end) {
                if (end == 7 || beg == 1) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            } else {
                if (beg == 1 || beg == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            }
        }
        return rs;
    }

    /**
     * 判断是否是同一个月
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static boolean isSameYearMonth(Date dt1, Date dt2) {
        if (dt1 == null || dt2 == null) {
            return false;
        }

        int tmp1 = yearMonth(dt1);
        int tmp2 = yearMonth(dt2);
        if (tmp1 == tmp2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSameYearMonth(String dt1, Date dt2) {
        if (dt1 == null || dt2 == null) {
            return false;
        }

        int tmp1 = yearMonth(dt1);
        int tmp2 = yearMonth(dt2);
        if (tmp1 == tmp2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSameYearMonth(Date dt1, String dt2) {
        if (dt1 == null || dt2 == null) {
            return false;
        }

        int tmp1 = yearMonth(dt1);
        int tmp2 = yearMonth(dt2);
        if (tmp1 == tmp2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSameYearMonth(String dt1, String dt2) {
        if (dt1 == null || dt2 == null) {
            return false;
        }

        int tmp1 = yearMonth(dt1);
        int tmp2 = yearMonth(dt2);
        if (tmp1 == tmp2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSameDay(Date dt1, Date dt2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt1);
        int y1 = cal.get(Calendar.YEAR);
        int m1 = cal.get(Calendar.MONTH);
        int d1 = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(dt2);
        int y2 = cal.get(Calendar.YEAR);
        int m2 = cal.get(Calendar.MONTH);
        int d2 = cal.get(Calendar.DAY_OF_MONTH);

        if (y1 == y2 && m1 == m2 && d1 == d2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 比较两个时间
     *
     * @param dt1
     * @param dt2
     * @return 0, 1, 2
     */
    public static boolean isDtGreaterThanToday(String dateStr) {
        String tmp[] = dateStr.split("-");
        if (tmp.length < 2) {
            return false;
        }
        int year = Integer.valueOf(tmp[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year2 = calendar.get(Calendar.YEAR);
        if (year > year2) {
            return true;
        } else if (year < year2) {
            return false;
        }

        int month = Integer.valueOf(tmp[1]);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        if (month > month2) {
            return true;
        } else if (month < month2) {
            return false;
        }

        int day = Integer.valueOf(tmp[2]);
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
        if (day > day2) {
            return true;
        }

        return false;
    }

    public static boolean isDt1GreaterThanDt2(String str1, String str2) {
        Date dt1 = DateUtil.string2Date(str1);
        Date dt2 = DateUtil.string2Date(str2);
        Long tm = dt1.getTime() - dt2.getTime();
        if (tm > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDt1GreaterThanOrEqualDt2(String str1, String str2) {
        Date dt1 = DateUtil.string2Date(str1);
        Date dt2 = DateUtil.string2Date(str2);
        Long tm = dt1.getTime() - dt2.getTime();
        if (tm >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDt1GreaterThanDt2ByYearMonth(String str1, String str2) {
        String[] list1 = str1.split("-");
        if (list1.length < 2) {
            return false;
        }

        String[] list2 = str2.split("-");
        if (list2.length < 2) {
            return false;
        }

        int year1 = Integer.valueOf(list1[0]);
        int month1 = Integer.valueOf(list1[1]);

        int year2 = Integer.valueOf(list2[0]);
        int month2 = Integer.valueOf(list2[1]);

        if (year1 > year2) {
            return true;
        } else if (year1 == year2 && month1 > month2) {
            return true;
        }

        return false;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(Date dt) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - dt.getTime() / 1000;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (DateUtil.isSameDay(dt, new Date())) {
            return "今天";
        } else {
            Date dt1 = DateUtil.getOffsetDate000000(new Date(), -5);// 昨天
            if (dt.getTime() > dt1.getTime()) {
                return "1天前";
            }
        }
        return "5天前";
    }

    public static void main(String[] args) {
        int week = DateUtil.workDaysToMonthEndDay("2017-02-2");
        System.out.println("" + week);

    }
}
