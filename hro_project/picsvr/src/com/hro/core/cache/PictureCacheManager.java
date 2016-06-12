package com.hro.core.cache;

import java.util.HashMap;
import java.util.Map;

public class PictureCacheManager {
	
	private static PictureCacheManager instance = null;
	
	private Map<String, byte[]> dataMap = new HashMap<String, byte[]>();
	
	private PictureCacheManager(){}
	
	private static class HolderClass{
		private static PictureCacheManager manager = new PictureCacheManager(); 
	}
	
	public static PictureCacheManager getInstance(){
		if(instance == null){
			instance = HolderClass.manager;
		}
		return instance;
	}
	
	public void addData(String key, byte[] data){
		dataMap.put(key, data);
	}
	
	public byte[] getData(String key){
		return dataMap.get(key);
	}
	
	public void removeData(String key){
		dataMap.remove(key);
	}
	
	public boolean isDataExist(String key){
		return dataMap.containsKey(key);
	}
	
	public void removeAll(){
		dataMap.clear();
	}
}
