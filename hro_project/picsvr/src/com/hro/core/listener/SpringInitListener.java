package com.hro.core.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hro.core.common.util.PicoContainerManager;
import com.hro.core.service.impl.PicInfoServiceImpl;
import com.hro.core.service.picsvr.PicInfoService;

@Component("springInitListener")
public class SpringInitListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	@Qualifier("m_picInfoService") 
	private PicInfoService picInfoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if(contextRefreshedEvent.getApplicationContext().getParent() == null){
			
			PicoContainerManager picoManager = PicoContainerManager.getInstance();
			picoManager.addComponent(picInfoService);
			
		}
	}
	
	public PicInfoService getPicInfoService() {
		return picInfoService;
	}

	public void setPicInfoService(PicInfoService picInfoService) {
		this.picInfoService = picInfoService;
	}
}
