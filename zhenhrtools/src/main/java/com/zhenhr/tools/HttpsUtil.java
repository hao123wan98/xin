package com.zhenhr.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;

public class HttpsUtil {

	@SuppressWarnings("finally")
	public static String runPostRequest(String urlStr, String json) {

		String result = null;
		URL url;
		HttpsURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "text/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();

			// Write the JSON query object to the connection output stream
			if (json != null) {
				OutputStreamWriter ps = new OutputStreamWriter(
						connection.getOutputStream());
				ps.write(json);
				ps.flush();
				ps.close();
			}

			// Call the service
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			// Extract response
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();

			String response = sb.toString();
			result = response;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.disconnect();
			return result;
		}
	}

	/**
	 * 获取get请求
	 */
	@SuppressWarnings("finally")
	public static String runGetRequest(String urlStr) {

		String result = null;
		URL url;
		try {
			url = new URL(urlStr);
			HttpsURLConnection connection = (HttpsURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("content-type", "text/json");
			connection.setDoOutput(true);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			// Extract response
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
			String response = sb.toString();
			result = response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	
	/**
	 * 返回参数错误
	 */
	public static void returnParamError(HttpServletResponse res) {
		try {

			res.setContentType("application/json;charset=UTF-8");

			String json = null;
			json = JsonUtil.resultErrorToJson("-1", "参数错误");

			PrintWriter out = res.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回值
	 */
	public static void returnJsonWithStr(String result, HttpServletResponse res) {
		try {

			res.setContentType("application/json;charset=UTF-8");

			String json = null;
			if (result != null) {
				json = JsonUtil.resultObjAndStrTojson(result);
			} else {
				json = JsonUtil.resultErrorToJson("-1", "数据错误");
			}

			PrintWriter out = res.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void returnJsonWithJson(String jsonStr,
			HttpServletResponse res) {
		try {

			res.setContentType("application/json;charset=UTF-8");

			String json = null;
			if (jsonStr != null) {
				json = "{\"code\":\"10000\",\"msg\":\"ok\",\"data\":" + jsonStr
						+ "}";
			} else {
				json = JsonUtil.resultErrorToJson("-1", "数据错误");
			}

			PrintWriter out = res.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
