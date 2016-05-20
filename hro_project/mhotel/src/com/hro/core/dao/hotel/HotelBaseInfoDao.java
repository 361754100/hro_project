package com.hro.core.dao.hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hro.core.common.log.MhotelLog;
import com.hro.core.common.util.QueryParams;
import com.hro.core.model.HotelBaseInfo;
import com.hro.core.model.mapper.HotelBaseInfoMapper;

@Repository
public class HotelBaseInfoDao {
	@Autowired
	private HotelBaseInfoMapper hotelBaseInfoMapper = null;
	
	/**
	 * 
	 * @param curNo
	 * @param curNext
	 * @return
	 */
	public List<HotelBaseInfo> findHotelInfoPage(QueryParams params){
		
		List<HotelBaseInfo> result = hotelBaseInfoMapper.findHotelInfoPage( params );
		
		MhotelLog.debug("[HotelBaseInfoDao.findHotelInfoPage] result->"+result);
		
		return result;
	}

	/**
	 * 
	 * @param curNo
	 * @param curNext
	 * @return
	 */
	public int findHotelInfoTotal(QueryParams params){
		Integer result = hotelBaseInfoMapper.findHotelInfoTotal( params);
		
		MhotelLog.debug("[HotelBaseInfoDao.findHotelInfoTotal] result->"+result);
		
		return result;
	}
	
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int saveHotelInfo(HotelBaseInfo hotelBaseInfo){
		int cnt = 0;
		cnt = hotelBaseInfoMapper.insert(hotelBaseInfo);
		return cnt;
	}
	
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int updateHotelInfo(HotelBaseInfo hotelBaseInfo){
		int cnt = 0;
		cnt = hotelBaseInfoMapper.updateByPrimaryKey(hotelBaseInfo);
		return cnt;
	}

	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int deleteHotelInfo(List<String> ids){
		int cnt = 0;
		cnt = hotelBaseInfoMapper.deleteByIds(ids);
		return cnt;
	}

	public HotelBaseInfoMapper getHotelBaseInfoMapper() {
		return hotelBaseInfoMapper;
	}

	public void setHotelBaseInfoMapper(HotelBaseInfoMapper hotelBaseInfoMapper) {
		this.hotelBaseInfoMapper = hotelBaseInfoMapper;
	}
	
}
