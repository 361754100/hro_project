package com.hro.core.common.log;

import org.apache.log4j.Logger;

public class MhotelLog {
	
	private static Logger logger = Logger.getLogger(MhotelLog.class);
	
	public static void info(String message){
		logger.info(message);
	}
	
	public static void debug(String message){
		logger.debug(message);
	}
	
	public static void error(String message){
		logger.error(message);
	}
	
	public static void error(String message, Exception e){
		logger.error(message, e);
	}
	
	public static void warn(String message){
		logger.warn(message);
	}
	
}
