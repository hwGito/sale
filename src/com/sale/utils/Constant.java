package com.sale.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

	
	//密匙
	public static String JWT_SECRET;
	
	//有效期string
	public static String JWT_EXP;//一小时

	//有效期long
	//public static Long JWT_EXP_LONG = new Long(JWT_EXP);
	
	@Value("${jwt.secret}")
	public void setJWT_SECRET(String jWT_SECRET) {
		JWT_SECRET = jWT_SECRET;
	}

	@Value("${jwt.exp}")
	public void setJWT_EXP(String jWT_EXP) {
		JWT_EXP = jWT_EXP;
	}

	

}
