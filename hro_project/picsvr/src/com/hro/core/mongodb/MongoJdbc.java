package com.hro.core.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.hro.core.common.AppHelper;
import com.mongodb.MongoClient;

public class MongoJdbc {
	
	private static MongoClient mongoClient = null;
	
	public static MongoTemplate getMongoTemplate(String databaseName){
		if( mongoClient == null ){
			MongoClient mongoClient =  new MongoClient(AppHelper.getConfig("mongodb.host"), Integer.parseInt(AppHelper.getConfig("mongodb.port")));
		}
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, databaseName);
		
		return mongoTemplate;
		
	}
	
}
