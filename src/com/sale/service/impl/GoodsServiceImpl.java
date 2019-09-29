package com.sale.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.mapper.GoodsMapper;
import com.sale.pojo.Goods;
import com.sale.service.GoodsService;

import net.sf.json.JSONObject;

@Service
public class GoodsServiceImpl implements GoodsService{

	private GoodsMapper goodsMapper;
	
	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}

	@Autowired
	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}


	@Override
	public JSONObject findAll(int page, int size,Map<String, Object> map) {//分类分页查询商品列表
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		int start = (page - 1) * size;
		json.put("total", goodsMapper.countAll(map));
		json.put("list",goodsMapper.findAll(start,size,map));
		return json;
	}

	@Override
	public JSONObject findById(String g_id) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("goods", goodsMapper.findById(g_id));
		return json;
	}

	@Override
	public JSONObject insertOne(Goods goods) {//插入一条数据
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(goodsMapper.insertOne(goods)>0) {
			json.put("info", "insert success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

	@Override
	public JSONObject updateOne(Goods goods) {//修改
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(goodsMapper.updateOne(goods)>0) {
			json.put("info", "update success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

	@Override
	public JSONObject deleteOne(String g_id) {//删除
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(goodsMapper.deleteOne(g_id)>0) {
			json.put("info", "delete success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

	

}
