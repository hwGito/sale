package com.sale.utils;

import org.springframework.web.servlet.ModelAndView;

public class ResponseJSON {
	/**
	 * 格式化返回json格式
	 * @param code 接口状态
	 * @param object data数据
	 * @param message 接口信息
	 * @param mav 模块
	 */
	public static void formatJSON(int code,Object object,String message,ModelAndView mav) {
		mav.addObject("code",code);
		mav.addObject("data",object);
		mav.addObject("message",message);
	}
}
