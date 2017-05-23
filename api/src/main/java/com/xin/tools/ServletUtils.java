package com.xin.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xin.tools.common.TPErrorCodeGeneral;

public class ServletUtils {
	// public static HttpSession getSession() {
	// HttpSession session = getRequest().getSession();
	// return session;
	// }

	// @SuppressWarnings("unchecked")
	// public static <X> X getSessionValue(String name) {
	// return (X) getSession().getAttribute(name);
	// }

	// public static HttpServletRequest getRequest() {
	// HttpServletRequest request = ((ServletRequestAttributes)
	// RequestContextHolder
	// .getRequestAttributes()).getRequest();
	// return request;
	// }

	public static void writeString(String str, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("返回time:" + new Date().getTime() + ",数据:" + str);

		String jsonp = req.getParameter("callbackjsonp");
		if (jsonp != null) {
			str = jsonp + "(" + str + ")";
		}

		initResponse(res);
		PrintWriter out;
		try {
			out = res.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {

		}
	}

	// public static ServletContext getServletContext() {
	// return getSession().getServletContext();
	// }

	public static void mapToJson(Map<String, ?> map, HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = JsonUtil.resultObjTojson(map);
		ServletUtils.writeString(jsonStr, req, res);
	}

	public static void listMapToJson(List<?> list, HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = JsonUtil.resultObjTojson(list);
		ServletUtils.writeString(jsonStr, req, res);
	}

	public static void strToJson(String dataStr, HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = JsonUtil.resultObjAndStrTojson(dataStr);
		ServletUtils.writeString(jsonStr, req, res);
	}

	public static void jsonStrToJson(String dataStr, HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = "{\"code\":\"" + TPErrorCodeGeneral.Succeed_Param + "\",\"msg\":\"ok\"";

		if (dataStr != null) {
			jsonStr += ",\"data\":" + dataStr;
		}

		jsonStr += "}";

		ServletUtils.writeString(jsonStr, req, res);
	}

	public static void toJson(HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = JsonUtil.resultObjTojson();
		ServletUtils.writeString(jsonStr, req, res);
	}

	public static void toJson(String key, Object value, HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = JsonUtil.resultObjTojson(key, value);
		ServletUtils.writeString(jsonStr, req, res);
	}

	public static void toJson(Object value, HttpServletRequest req, HttpServletResponse res) {
		String jsonStr = JsonUtil.resultObjTojson(value);
		ServletUtils.writeString(jsonStr, req, res);
	}

	private static void initResponse(HttpServletResponse res) {
		res.setContentType("application/json;charset=UTF-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Request-Method", "POST");
		res.setHeader("Access-Control-Request-Headers", "token");
	}
}
