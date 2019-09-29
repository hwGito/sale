package com.sale.utils;

public class RandomUtil {
	/**
	 * 随机10位数
	 * @return
	 */
	public static String RandomNum10() {
		String code = "";
		Integer random1 = (int)((Math.random()*9+1)*10000);
        Integer random2 = (int)((Math.random()*9+1)*10000);
        code = random1.toString()+random2.toString();
		return code ;
	}
	
}
