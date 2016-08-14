package com.hro.core.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hro.core.mongo.vo.PicInfo;
import com.hro.core.service.picsvr.PicInfoService;

@Service("m_picInfoService")
public class PicInfoServiceImpl implements PicInfoService {
	
	@Autowired
	private AdvancedDatastore mongodbStore;
	
	@Override
	public int savePicInfo(PicInfo picInfo) throws Exception {
		int cnt = 0;
		try {
			mongodbStore.save(picInfo);
			cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public List<PicInfo> getPicInfoByTypeGroup(int type, String groupId){
		Query<PicInfo> query = mongodbStore.createQuery(PicInfo.class);
		query.filter("picType", type);
		query.filter("groupId", groupId);
		
		AggregationPipeline pipeline = mongodbStore.createAggregation(PicInfo.class).match(query);
		Iterator<PicInfo> picInfoIter = pipeline.aggregate(PicInfo.class);
		
		List<PicInfo> picInfoList = new ArrayList<PicInfo>();
		while(picInfoIter.hasNext()){
			picInfoList.add(picInfoIter.next());
		}
		return picInfoList;
	}

	public AdvancedDatastore getMongodbStore() {
		return mongodbStore;
	}

	public void setMongodbStore(AdvancedDatastore mongodbStore) {
		this.mongodbStore = mongodbStore;
	}

	
}
