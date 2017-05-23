package com.zhenhr.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OSSUtil {
	
	private static final String _OSS_URL_FILE =  "http://file.yiart.cn";
	
	private static List<String> _OSS_PIC_EXT = new ArrayList<String>(Arrays.asList(("jpg,jpeg").split(",")));
	private static final String _OSS_PATH_FORMAT = "yyyy/MM/dd";
	
	/**
	 * 对客户端上传的URL 去掉域名信息，返回Path
	 * @param url
	 * @return
	 */
	public static String url2Path(String url){
		if(url.toLowerCase().trim().startsWith(_OSS_URL_FILE.toLowerCase().trim())){
			url =  url.substring(_OSS_URL_FILE.length()+1);
			return url;
		}
		
		return url;
		
	}
	
	/**
	  * 利用数据库中文件的PATH，返回文件的URL
	  * path2URL(这里用一句话描述这个方法的作用)
	  *
	  * @Title: path2URL
	  * @Description: 
	  * @param @param path
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String path2URL(String path){
		
		if(null == path || "".equals(path) ){
			return null;
		}
		
		String ext = path.substring(path.lastIndexOf(".")+1).toLowerCase().trim();

		if(path.toLowerCase().trim().startsWith("http://") ||path.toLowerCase().trim().startsWith("https://")){
			return path;
		}
		
		for(String str:_OSS_PIC_EXT){
			
			str = str.toLowerCase().trim();
			
			if(str.equals(ext) ){
				
				if(path.startsWith("/" )){
					return  _OSS_URL_FILE+path;
				}else{
					return _OSS_URL_FILE +"/" + path;
				}
			}
			
		}
		
		if(path.startsWith("/")){
			return  _OSS_URL_FILE+path;
		}else{
			return _OSS_URL_FILE +"/" + path;
		}
	}
	 public static String pathToUrl(String path){
	    	String url=path;
	    	if(!path.startsWith("http://video.mele.tv/")){
	    	    url="http://video.mele.tv/"+path;
	    	}
	    	return url;
	    }
	/**
	 * 根据filename ext 获取KEY
	 * @return
	 */
	public static String productKey(String filename,String ext){
		
		String name = "";
		
		String date = new SimpleDateFormat(_OSS_PATH_FORMAT).format(new Date());
		
		if(filename == null || "".equals(filename.trim())){
			name = UUID.randomUUID().toString().replace("-", "");
		}else{
			name = filename ;
		} 
		
		if(ext == null || "".equals(ext.trim())){
			return new StringBuffer().append(date).append("/").append(name).toString();
		}else{
			return new StringBuffer().append(date).append("/").append(name).append(".").append(ext).toString();
		}
	}
	
	/**
	 * 根据一个URL，反解出 bucketName
	 * @param url
	 * @return
	 */
	public static String urlTobucketName(String url){
		//http://mes-test.oss.aliyuncs.com/2014/10/22/QQ201407041418148.jpg?Expires=1413977370&OSSAccessKeyId=nGi0VQZowMQeWCh6&Signature=CTs1kBu3mkkEprXAb%2BPtYs1bQnE%3D
		return url.substring(7).split("\\.")[0];
	}
	
	/**
	 * 根据一个URL，反解出KEY
	 * @param url
	 * @return
	 */
	public static String urlToKEY(String url){
		//http://mes-test.oss.aliyuncs.com/2014/10/22/QQ201407041418148.jpg?Expires=1413977370&OSSAccessKeyId=nGi0VQZowMQeWCh6&Signature=CTs1kBu3mkkEprXAb%2BPtYs1bQnE%3D
		String temp = url.substring(7);
		String key = temp.substring(temp.indexOf("/")+1);
		return key;
	}

	public static void main(String[] args){
		System.out.println(path2URL("2014/11/05/63be041966594bada46a1f1256b193de.jpg"));
		
		String str1 = "useresr/fesfes/efesf.Jpg";
		
		String str2 = "useresr" ;
		
		System.out.println(str1.substring(str2.length()));
		
	}
}
