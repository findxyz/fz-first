package com.fz.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring context �����࣬�������κ����л�ȡ��Ҫʹ�õ�bean
 * 
 * @author fz
 * @time 2011-7-6
 */
public class SpringContextHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	// ��־
	protected transient final Log log = LogFactory.getLog(getClass());

	/*
	 * ע��ApplicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// �ڼ���Springʱ�Զ����applicationContext
		SpringContextHelper.applicationContext = applicationContext;
	}

	/*
	 * ��ȡbean
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}