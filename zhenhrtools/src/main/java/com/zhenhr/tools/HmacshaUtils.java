package com.zhenhr.tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HmacshaUtils {

	/**
	 * @creator sgy
	 * @createTime:2015年1月22日 下午6:42:21 ==edit===========
	 * @updateTime 2015年1月22日 下午6:42:21
	 * @Description: 填写修改内容 ==============
	 * @param data
	 * @param key
	 * @param type
	 *            1,256,384,512
	 * @return
	 */
	public static String getToken(String data, String key, Integer type) {
		try {
			byte[] secretByte = data.getBytes("UTF-8");
			byte[] dataBytes = key.getBytes("UTF-8");
			Mac mac;
			if (type == 1) {
				mac = Mac.getInstance("HmacSHA1");
			} else if (type == 256) {
				mac = Mac.getInstance("HmacSHA256");
			} else if (type == 384) {
				mac = Mac.getInstance("HmacSHA384");
			} else if (type == 512) {
				mac = Mac.getInstance("HmacSHA512");
			}else{
				return null;
			}
			SecretKey secret = new SecretKeySpec(secretByte, mac.getAlgorithm());
			mac.init(secret);
			byte[] doFinal = mac.doFinal(dataBytes);
			byte[] hexB = new Hex().encode(doFinal);
			String checksum = new String(hexB);
			return checksum;
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (InvalidKeyException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}

	}

}
