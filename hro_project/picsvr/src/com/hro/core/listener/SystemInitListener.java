package com.hro.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hro.core.cache.PictureCacheManager;
import com.hro.core.common.AppHelper;

public class SystemInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//初始化图片缓存单例
		PictureCacheManager.getInstance();
		//AppHelper初始化配置信息
		AppHelper.initConfig();
	}

}
