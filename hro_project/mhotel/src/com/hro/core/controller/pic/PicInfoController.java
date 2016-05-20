package com.hro.core.controller.pic;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hro.core.model.PicInfo;
import com.hro.core.service.impl.PicInfoServiceImpl;

@Controller
@RequestMapping("/pic")
public class PicInfoController {
	
	@Autowired
	private PicInfoServiceImpl picInfoService = null;
	
	@RequestMapping(value="/savePicInfo.do", method=RequestMethod.GET)
//	public @ResponseBody Map<String,Object> queryHotelInfo(@RequestBody Map<String,Object> params, HttpServletRequest request){
	public @ResponseBody Map<String,Object> savePicInfo(HttpServletRequest request){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		PicInfo picInfo = new PicInfo();
		picInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		picInfo.setFkId("874d6e6cbc0248288cdea05d0ccd7c01");
		picInfo.setPicName("dubaiHotel.jpg");
		picInfo.setPicType(0);
		
		int cnt = picInfoService.savePicInfo(picInfo);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}

	public PicInfoServiceImpl getPicInfoService() {
		return picInfoService;
	}

	public void setPicInfoService(PicInfoServiceImpl picInfoService) {
		this.picInfoService = picInfoService;
	}
	
}
