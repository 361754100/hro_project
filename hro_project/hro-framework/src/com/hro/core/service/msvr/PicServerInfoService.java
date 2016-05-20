package com.hro.core.service.msvr;

import java.util.List;

import com.hro.core.common.util.QueryParams;
import com.hro.core.model.PicServerInfo;

public interface PicServerInfoService {
	
	/**
	 * 分页服务器信息
	 * @param params
	 * @return
	 */
	public List<PicServerInfo> findPicServerInfoPage(QueryParams params);
	
	/**
	 * 根据查询条件统计记录总数
	 * @param params
	 * @return
	 */
	public int findPicServerInfoTotal(QueryParams params);
	
	/**
	 * 保存服务器信息
	 * @param hotelBaseInfo
	 * @return
	 */
	public int savePicServerInfo(PicServerInfo picServerInfo);
	
	/**
	 * 更新服务器信息
	 * @param hotelBaseInfo
	 * @return
	 */
	public int updatePicServerInfo(PicServerInfo picServerInfo);
	
	/**
	 * 删除服务器信息
	 * @param ids
	 * @return
	 */
	public int deletePicServerInfo(List<String> ids);
}
