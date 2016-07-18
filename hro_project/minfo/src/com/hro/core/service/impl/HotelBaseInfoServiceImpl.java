package com.hro.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hro.core.common.util.QueryParams;
import com.hro.core.dao.hotel.HotelBaseInfoDao;
import com.hro.core.model.HotelBaseInfo;
import com.hro.core.service.mhotel.HotelBaseInfoService;

/**
 * 
 * @author mjzhang
 *
 */
@Service("hotelBaseInfoService")
@Transactional
public class HotelBaseInfoServiceImpl implements HotelBaseInfoService{
	
	@Autowired
	private HotelBaseInfoDao hotelBaseInfoDao = null;
	
	public List<HotelBaseInfo> findHotelInfoPage(QueryParams params){
		List<HotelBaseInfo> result = hotelBaseInfoDao.findHotelInfoPage(params);
		return result;
	}
	
	public int findHotelInfoTotal(QueryParams params){
		int result = hotelBaseInfoDao.findHotelInfoTotal(params);
		return result;
	}
	
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int saveHotelInfo(HotelBaseInfo hotelBaseInfo){
		int cnt = 0;
		if(hotelBaseInfo != null){
			cnt = hotelBaseInfoDao.saveHotelInfo(hotelBaseInfo);
		}
		return cnt;
	}
	
	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int updateHotelInfo(HotelBaseInfo hotelBaseInfo){
		int cnt = 0;
		if(hotelBaseInfo != null){
			cnt = hotelBaseInfoDao.updateHotelInfo(hotelBaseInfo);
		}
		return cnt;
	}

	/**
	 * 
	 * @param hotelBaseInfo
	 * @return
	 */
	public int deleteHotelInfo(List<String> ids){
		int cnt = 0;
		if(ids != null){
			cnt = hotelBaseInfoDao.deleteHotelInfo(ids);
		}
		return cnt;
	}

	public HotelBaseInfoDao getHotelBaseInfoDao() {
		return hotelBaseInfoDao;
	}

	public void setHotelBaseInfoDao(HotelBaseInfoDao hotelBaseInfoDao) {
		this.hotelBaseInfoDao = hotelBaseInfoDao;
	}
	
}
