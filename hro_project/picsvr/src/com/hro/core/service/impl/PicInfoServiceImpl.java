package com.hro.core.service.impl;

import org.mongodb.morphia.AdvancedDatastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hro.core.model.PicInfo;
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

	public AdvancedDatastore getMongodbStore() {
		return mongodbStore;
	}

	public void setMongodbStore(AdvancedDatastore mongodbStore) {
		this.mongodbStore = mongodbStore;
	}

	
}
