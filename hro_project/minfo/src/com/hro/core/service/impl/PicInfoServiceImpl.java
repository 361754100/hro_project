package com.hro.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hro.core.dao.pic.PicInfoDao;
import com.hro.core.model.PicInfo;
import com.hro.core.service.mhotel.PicInfoService;

@Service("picInfoService")
@Transactional
public class PicInfoServiceImpl implements PicInfoService{
	
	@Autowired
	private PicInfoDao picInfoDao = null;
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int savePicInfo(PicInfo picInfo){
		int cnt = 0;
		if(picInfo != null){
			cnt = picInfoDao.savePicInfo(picInfo);
		}
		return cnt;
	}
}
