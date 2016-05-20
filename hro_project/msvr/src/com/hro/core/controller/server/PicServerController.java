package com.hro.core.controller.server;

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

import com.hro.core.common.util.QueryParams;
import com.hro.core.common.util.StringUtil;
import com.hro.core.model.HotelBaseInfo;
import com.hro.core.model.PicServerInfo;
import com.hro.core.service.msvr.PicServerInfoService;

@Controller
@RequestMapping("/picserver")
public class PicServerController {
	
	@Autowired
	private PicServerInfoService picServerInfoService;
	
	@RequestMapping(value="/queryInfoPage", method=RequestMethod.POST  )
	public @ResponseBody Map<String,Object> queryInfoPage( HttpServletRequest request){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		Integer start = Integer.valueOf(request.getParameter("start"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		Integer draw = Integer.valueOf(request.getParameter("draw"));
		
		String orderColumn = request.getParameter("order[0][column]");
		String orderColumnName = request.getParameter("columns["+orderColumn+"][name]");
		String orderDir = request.getParameter("order[0][dir]");
		
		Map<String, Object> formParam = new HashMap<String, Object>();
		formParam.put("serverName", StringUtil.toString(request.getParameter("serverName")));
		formParam.put("serverIp", StringUtil.toString(request.getParameter("serverIp")));
		
		QueryParams query = new QueryParams();
		query.setCurRecordNo(start);
		query.setEndRecordNo(length);
		query.setParams(formParam);
		query.setOrderColumn(orderColumnName);
		query.setOrderDir(orderDir);
		
		List<PicServerInfo> result = picServerInfoService.findPicServerInfoPage(query);
		int total = picServerInfoService.findPicServerInfoTotal(query);
		
		rtMap.put("draw",  draw+1);
		rtMap.put("recordsTotal", total);
		rtMap.put("recordsFiltered", total);
		rtMap.put("data", result);

		return rtMap;
	}
	
	@RequestMapping(value="/savePicServerInfo", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> savePicServerInfo(@RequestBody Map<String,Object> params){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		PicServerInfo picServerInfo = new PicServerInfo();
		picServerInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		picServerInfo.setServerName(StringUtil.toString(params.get("serverName")));
		picServerInfo.setServerIp(StringUtil.toString(params.get("serverIp")));
		picServerInfo.setServerPort(StringUtil.toString(params.get("serverPort")));
		
		int cnt = picServerInfoService.savePicServerInfo(picServerInfo);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}
	
	@RequestMapping(value="/updatePicServerInfo", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updatePicServerInfo(@RequestBody Map<String,Object> params ){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		PicServerInfo picServerInfo = new PicServerInfo();
		picServerInfo.setId(StringUtil.toString(params.get("serverId")));
		picServerInfo.setServerName(StringUtil.toString(params.get("serverName")));
		picServerInfo.setServerIp(StringUtil.toString(params.get("serverIp")));
		picServerInfo.setServerPort(StringUtil.toString(params.get("serverPort")));
		
		int cnt = picServerInfoService.updatePicServerInfo(picServerInfo);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}
	
	@RequestMapping(value="/deletePicServerInfo", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deletePicServerInfo(@RequestBody Map<String,Object> params){
		Map<String,Object> rtMap = new HashMap<String,Object>();
		
		String ids = StringUtil.toString(params.get("ids"));
		String idArr[] = ids.split(",");
		List<String> idList = new ArrayList(Arrays.asList(idArr));
		
		int cnt = picServerInfoService.deletePicServerInfo(idList);
		
		rtMap.put("cnt", cnt);
		return rtMap;
	}

	public PicServerInfoService getPicServerInfoService() {
		return picServerInfoService;
	}

	public void setPicServerInfoService(PicServerInfoService picServerInfoService) {
		this.picServerInfoService = picServerInfoService;
	}
	
}
