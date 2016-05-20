package com.hro.core.service.mhotel;

import java.util.List;

import com.hro.core.common.util.QueryParams;
import com.hro.core.model.HotelBaseInfo;

public interface HotelBaseInfoService {
	
	/**
	 * 分页查询酒店信息
	 * @param params
	 * @return
	 */
	public List<HotelBaseInfo> findHotelInfoPage(QueryParams params);
	
	/**
	 * 根据查询条件统计记录总数
	 * @param params
	 * @return
	 */
	public int findHotelInfoTotal(QueryParams params);
	
	/**
	 * 保存酒店信息
	 * @param hotelBaseInfo
	 * @return
	 */
	public int saveHotelInfo(HotelBaseInfo hotelBaseInfo);
	
	/**
	 * 更新酒店信息
	 * @param hotelBaseInfo
	 * @return
	 */
	public int updateHotelInfo(HotelBaseInfo hotelBaseInfo);
	
	/**
	 * 删除酒店信息
	 * @param ids
	 * @return
	 */
	public int deleteHotelInfo(List<String> ids);
}
