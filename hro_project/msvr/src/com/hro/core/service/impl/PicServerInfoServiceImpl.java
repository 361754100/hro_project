package com.hro.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hro.core.common.util.QueryParams;
import com.hro.core.dao.server.PicServerInfoDao;
import com.hro.core.model.PicServerInfo;
import com.hro.core.service.msvr.PicServerInfoService;

@Service("picServerInfoService")
@Transactional
public class PicServerInfoServiceImpl implements PicServerInfoService{
	
	@Autowired
	private PicServerInfoDao picServerInfoDao;
	
	@Override
	public List<PicServerInfo> findPicServerInfoPage(QueryParams params) {
		List<PicServerInfo> result = picServerInfoDao.findPicServerInfoPage(params);
		return result;
	}

	@Override
	public int findPicServerInfoTotal(QueryParams params) {
		int result = picServerInfoDao.findPicServerInfoTotal(params);
		return result;
	}

	@Override
	public int savePicServerInfo(PicServerInfo picServerInfo) {
		int cnt = 0;
		if(picServerInfo != null){
			cnt = picServerInfoDao.savePicServerInfo(picServerInfo);
		}
		return cnt;
	}

	@Override
	public int updatePicServerInfo(PicServerInfo picServerInfo) {
		int cnt = 0;
		if(picServerInfoDao != null){
			cnt = picServerInfoDao.updatePicServerInfo(picServerInfo);
		}
		return cnt;
	}

	@Override
	public int deletePicServerInfo(List<String> ids) {
		int cnt = 0;
		if(ids != null){
			cnt = picServerInfoDao.deletePicServerInfo(ids);
		}
		return cnt;
	}

}
