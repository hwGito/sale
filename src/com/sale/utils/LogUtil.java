package com.sale.utils;

import org.apache.log4j.Logger;

public class LogUtil{
	private LogUtil() {
		
	}
	
	private static Logger log = Logger.getLogger(LogUtil.class);
	
	/**
	 * 接口info输出
	 * @param msg信息
	 * @param loc接口
	 */
	public static void info(String msg,String loc) {
		log.info("[LogUtil.info("+loc+")]"+msg);
	}
	/**
	 * 接口error输出
	 * @param msg信息
	 * @param loc接口
	 */
	public static void error(String msg,String loc) {
		log.error("[LogUtil.error("+loc+")]"+msg);
	}
	/**
	 * 接口warn输出
	 * @param msg信息
	 * @param loc接口
	 */
	public static void warn(String msg,String loc) {
		log.warn("[LogUtil.warn("+loc+")]"+msg);
	}
}
