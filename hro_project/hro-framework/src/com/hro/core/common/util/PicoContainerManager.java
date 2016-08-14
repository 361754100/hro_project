package com.hro.core.common.util;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class PicoContainerManager {

	private static final MutablePicoContainer pico = new DefaultPicoContainer();
	
	private static PicoContainerManager instance = null;
	
	private PicoContainerManager(){}
	
	private static class HolderClass{
		private static PicoContainerManager manager = new PicoContainerManager();
	}
	
	public static PicoContainerManager getInstance(){
		if(instance == null){
			instance = HolderClass.manager;
		}
		return instance;
	}
	
	public void addComponent(Object obj){
		pico.addComponent(obj);
	}
	
	public <T> Object getComponent(Class<T> classz){
		Object obj = pico.getComponent(classz);
		return obj;
	}
}
