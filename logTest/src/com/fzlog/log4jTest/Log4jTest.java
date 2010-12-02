package com.fzlog.log4jTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4jTest {
	
	/**
	 * ��־
	 */
	protected final Log log = LogFactory.getLog(getClass());
	//ʹ�þ�̬������ʼ����ʱ��,������getClass()��������
	//private static Log log = LogFactory.getLog(Log4jTest.class);
	
	public void logTest(){
		log.info("infoTest");
		log.debug("debugTest");
		log.error("errorTest");
		log.trace("traceTest");
		log.warn("warnTest");
		log.fatal("fatalTest");
	}
	
	public static void main(String[] args){
		Log4jTest log4jTest = new Log4jTest();
		log4jTest.logTest();
	}
}
