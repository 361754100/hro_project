package com.hro.core.dao.pic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hro.core.model.PicInfo;
import com.hro.core.model.mapper.PicInfoMapper;

@Repository
public class PicInfoDao {
	
	@Autowired
	private PicInfoMapper picInfoMapper = null;

	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int savePicInfo(PicInfo picInfo){
		int cnt = 0;
		cnt = picInfoMapper.insert(picInfo);
		return cnt;
	}
	
	public PicInfoMapper getPicInfoMapper() {
		return picInfoMapper;
	}

	public void setPicInfoMapper(PicInfoMapper picInfoMapper) {
		this.picInfoMapper = picInfoMapper;
	}
	
}
