package com.hro.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.hro.core.common.util.StringUtil;
/**
 * 此类用于读取应用的配置文件
 * @author user
 *
 */
public class AppHelper {
	
	private static Map<String, String> configMap = new HashMap<String, String>();
	
	public static void initConfig(){
		Properties prop = new Properties(); 
		InputStream in = AppHelper.class.getResourceAsStream( "/config.properties" ); 
		try { 
			prop.load(in); 
			
			Set<Object> keySet = prop.keySet();
			if(keySet != null){
				Iterator<Object> iterator = keySet.iterator();
				while(iterator.hasNext()){
					String key = StringUtil.toString(iterator.next());
					String value = StringUtil.trim(prop.getProperty(key));
					
					configMap.put(key, value);
				}
			}
			
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
	
	
	public static String getConfig(String key){
		return configMap.get(key);
	}
}
