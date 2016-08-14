package com.hro.core.controller.hotel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hro.core.common.util.PicoContainerManager;
import com.hro.core.common.util.QueryParams;
import com.hro.core.common.util.StringUtil;
import com.hro.core.model.HotelBaseInfo;
import com.hro.core.mongo.vo.PicInfo;
import com.hro.core.mongo.vo.PicTypeEnum;
import com.hro.core.service.impl.PicInfoServiceImpl;
import com.hro.core.service.mhotel.HotelBaseInfoService;
import com.hro.core.service.picsvr.PicInfoService;

@Controller
@RequestMapping("/hotel")
public class HotelContorller {
	
	@Autowired
	private HotelBaseInfoService hotelBaseInfoService = null;
	
	@RequestMapping(value="/queryHotelInfo.do", method=RequestMethod.POST  )
	public @ResponseBody Map<String,Object> queryHotelInfo( HttpServletRequest request){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		Integer start = Integer.valueOf(request.getParameter("start"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		Integer draw = Integer.valueOf(request.getParameter("draw"));
		
		String orderColumn = request.getParameter("order[0][column]");
		String orderColumnName = request.getParameter("columns["+orderColumn+"][name]");
		String orderDir = request.getParameter("order[0][dir]");
		
		Map<String, Object> formParam = new HashMap<String, Object>();
		formParam.put("hotelName", StringUtil.toString(request.getParameter("hotelName")));
		formParam.put("picName", StringUtil.toString(request.getParameter("picName")));
		
		QueryParams query = new QueryParams();
		query.setCurRecordNo(start);
		query.setEndRecordNo(length);
		query.setParams(formParam);
		query.setOrderColumn(orderColumnName);
		query.setOrderDir(orderDir);
		
		List<HotelBaseInfo> result = hotelBaseInfoService.findHotelInfoPage(query);
		int total = hotelBaseInfoService.findHotelInfoTotal(query);
		
		rtMap.put("draw",  draw+1);
		rtMap.put("recordsTotal", total);
		rtMap.put("recordsFiltered", total);
		rtMap.put("data", result);

		return rtMap;
	}
	
	@RequestMapping(value="/saveHotelInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveHotelInfo(@RequestBody Map<String,Object> params){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
//		System.out.println("params --->"+params);
		
		HotelBaseInfo hotelBaseInfo = new HotelBaseInfo();
		hotelBaseInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		hotelBaseInfo.setIntroduction(StringUtil.toString(params.get("hotelIntroduction")));
		hotelBaseInfo.setHotelName(StringUtil.toString(params.get("hotelName")));
		hotelBaseInfo.setAddress(StringUtil.toString(params.get("hotelAddress")));
		hotelBaseInfo.setBdX(new BigDecimal(0));
		hotelBaseInfo.setBdY(new BigDecimal(0));
		
		int cnt = hotelBaseInfoService.saveHotelInfo(hotelBaseInfo);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}
	
	@RequestMapping(value="/updateHotelInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateHotelInfo(@RequestBody Map<String,Object> params ){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		HotelBaseInfo hotelBaseInfo = new HotelBaseInfo();
		hotelBaseInfo.setId(StringUtil.toString(params.get("hotelId")));
		hotelBaseInfo.setIntroduction(StringUtil.toString(params.get("hotelIntroduction")));
		hotelBaseInfo.setHotelName(StringUtil.toString(params.get("hotelName")));
		hotelBaseInfo.setAddress(StringUtil.toString(params.get("hotelAddress")));
		
		int cnt = hotelBaseInfoService.updateHotelInfo(hotelBaseInfo);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}
	
	@RequestMapping(value="/deleteHotelInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteHotelInfo(@RequestBody Map<String,Object> params){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		String ids = StringUtil.toString(params.get("ids"));
		String idArr[] = ids.split(",");
		List<String> idList = new ArrayList(Arrays.asList(idArr));
		
		int cnt = hotelBaseInfoService.deleteHotelInfo(idList);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}
	
	@RequestMapping(value="/getHotelPicInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getHotelPicInfo(@RequestBody Map<String,Object> params){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		String groupId = StringUtil.toString(params.get("groupId"));
		String picType = StringUtil.toString(params.get("picType"));
		
		PicoContainerManager picoManager = PicoContainerManager.getInstance();
		PicInfoService picInfoService = (PicInfoService) picoManager.getComponent(PicInfoService.class);
		
		List<PicInfo> picInfoList = picInfoService.getPicInfoByTypeGroup(PicTypeEnum.valueOf(picType).getValue(), groupId);
		
		rtMap.put("picInfoList", picInfoList);
		return rtMap;
	}
	

	public HotelBaseInfoService getHotelBaseInfoService() {
		return hotelBaseInfoService;
	}

	public void setHotelBaseInfoService(HotelBaseInfoService hotelBaseInfoService) {
		this.hotelBaseInfoService = hotelBaseInfoService;
	}
	
}
