package com.hro.core.dao.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hro.core.common.log.MsvrLog;
import com.hro.core.common.util.QueryParams;
import com.hro.core.model.PicServerInfo;
import com.hro.core.model.mapper.PicServerInfoMapper;


@Repository
public class PicServerInfoDao {
	
	@Autowired
	private PicServerInfoMapper picServerInfoMapper = null;
	
	/**
	 * 
	 * @param curNo
	 * @param curNext
	 * @return
	 */
	public List<PicServerInfo> findPicServerInfoPage(QueryParams params){
		
		List<PicServerInfo> result = picServerInfoMapper.findInfoPage( params );
		
		MsvrLog.debug("[PicServerInfoDao.findPicServerInfoPage] result->"+result);
		
		return result;
	}

	/**
	 * 
	 * @param curNo
	 * @param curNext
	 * @return
	 */
	public int findPicServerInfoTotal(QueryParams params){
		Integer result = picServerInfoMapper.findPageInfoTotal( params);
		
		MsvrLog.debug("[PicServerInfoDao.findPicServerInfoTotal] result->"+result);
		
		return result;
	}
	
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int savePicServerInfo(PicServerInfo picServerInfo){
		int cnt = 0;
		cnt = picServerInfoMapper.insert(picServerInfo);
		return cnt;
	}
	
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int updatePicServerInfo(PicServerInfo picServerInfo){
		int cnt = 0;
		cnt = picServerInfoMapper.updateByPrimaryKeySelective(picServerInfo);
		return cnt;
	}

	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int deletePicServerInfo(List<String> ids){
		int cnt = 0;
		cnt = picServerInfoMapper.deleteByIds(ids);
		return cnt;
	}
	

	public PicServerInfoMapper getPicServerInfoMapper() {
		return picServerInfoMapper;
	}

	public void setPicServerInfoMapper(PicServerInfoMapper picServerInfoMapper) {
		this.picServerInfoMapper = picServerInfoMapper;
	}
	
}
