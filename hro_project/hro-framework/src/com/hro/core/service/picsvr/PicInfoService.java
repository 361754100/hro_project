package com.hro.core.service.picsvr;

import java.util.List;

import com.hro.core.mongo.vo.PicInfo;

public interface PicInfoService {
	
	/**
	 * 保存图片基本信息到mongodb
	 * @param picInfo
	 * @return
	 * @throws Exception
	 */
	public int savePicInfo(PicInfo picInfo) throws Exception;
	
	
	/**
	 * 根据图片类型和分组ID，从mongodb查询出相应结果
	 * @param type
	 * @param groupId
	 * @return
	 */
	public List<PicInfo> getPicInfoByTypeGroup(int type, String groupId);
}
