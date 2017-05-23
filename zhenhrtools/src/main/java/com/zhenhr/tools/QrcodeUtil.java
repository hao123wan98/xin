package com.zhenhr.tools;

import java.awt.image.BufferedImage;

/**
 * 生成二维码
 * 
 * @author guoyongshi
 * 
 */
public class QrcodeUtil {

	/**
	 * 生成
	 * 
	 * @param url
	 * @return
	 */
	public static BufferedImage encoderQRCode(String url) {
		BufferedImage bufImg = null;
		try {
			bufImg = QRCode.qRCodeCommon(url, "png", 7);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufImg;
	}

}
