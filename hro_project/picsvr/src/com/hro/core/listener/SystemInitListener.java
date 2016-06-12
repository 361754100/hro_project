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
		//��ʼ��ͼƬ���浥��
		PictureCacheManager.getInstance();
		//AppHelper��ʼ��������Ϣ
		AppHelper.initConfig();
	}

}
