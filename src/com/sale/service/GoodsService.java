package com.sale.service;

import java.util.Map;

import com.sale.pojo.Goods;

import net.sf.json.JSONObject;

public interface GoodsService {


	JSONObject findAll(int page, int size, Map<String, Object> map);

	JSONObject findById(String g_id);

	JSONObject insertOne(Goods goods);

	JSONObject updateOne(Goods goods);

	JSONObject deleteOne(String g_id);
	
}
