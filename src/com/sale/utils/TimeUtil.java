package com.sale.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * 生成8位时间戳+5位随机数
	 * @param timeState
	 * @return
	 */
	public static String number5AndTime8(String timeState) {
		String code = "";
		Integer num = (int)((Math.random()*9+1)*10000);
		String time = timeState.substring(timeState.length() - 8);
		code = time + num;
		return code;
	}
	
	/**
	 * 时间戳转换 yyyyMMddhhmmss（14位）
	 * @param timeState
	 * @return
	 */
	public static String timeStateCon(String timeState) {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
		return ft.format(new Date(Long.valueOf(timeState)));
	}
	/**
	 * 获取时间戳（10位）
	 * @return
	 */
	public static String getTimeState() {
		long lon = System.currentTimeMillis()/1000l;
		return Long.toString(lon);
	}
}
