package com.zhenhr.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;

public class EntityUtils {

	static public String toString(HttpEntity entity) throws IllegalStateException, IOException{
		
		StringBuilder body = new StringBuilder(); 
         
		BufferedReader bufferedReader2 = new BufferedReader( 
                new InputStreamReader(entity.getContent())); 
        for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2 
                .readLine()) { 
       	 body.append(s); 
        }
        
        return body.toString(); 
        
	}
}
