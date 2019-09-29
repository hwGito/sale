package com.sale.service;

import java.util.Map;

import com.sale.pojo.User;

import net.sf.json.JSONObject;

public interface AdminService {
	
	JSONObject findAllUser(int page, int size, Map<String, Object> map);

	JSONObject insertOne(User user);

	JSONObject updateOne(User user);

	JSONObject deleteOne(String u_id);

}
