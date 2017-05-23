package com.xin.tools.common;

/**
 * 错误码
 * 
 * @author sgy
 * 
 */
public class TPErrorCodeGeneral {
	// 返回成功＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
	public static final String Succeed_Param = "10000";

	// 接口传递的参数错误＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
	public static final String Error_Param = "20000";

	// 服务器内部错误
	public static final String Error_Service = "30000";

	// 需要通知用户知道的错误
	public static final String Error_ToUser = "40000";
	public static final String Error_ToUser_Input_PicCode = "40001"; // 错误次数太多，需要输入图形验证码
	public static final String Error_ToUser_Input_SmsCode = "40002";// 错误次数太多，需要输入短信验证码
	public static final String Error_ToUser_Invalid_Mobile = "40003";// 无效的手机号
	public static final String Error_ToUser_Invalid_PWD = "40004";// 无效的密码

	public static final String Error_ToUser_No_Company = "41000";// 必须先创建企业信息

	// 未知的错误
	public static final String Error_Unknown = "50000";

	// token 错误
	public static final String Error_TokenError = "90000";

}
