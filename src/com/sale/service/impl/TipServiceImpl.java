package com.sale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.mapper.TipMapper;
import com.sale.service.TipService;

import net.sf.json.JSONObject;

@Service
public class TipServiceImpl implements TipService{

	private TipMapper tipMapper;
	
	public TipMapper getTipMapper() {
		return tipMapper;
	}

	@Autowired
	public void setTipMapper(TipMapper tipMapper) {
		this.tipMapper = tipMapper;
	}

	@Override
	public JSONObject getTip() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("tip", tipMapper.getTip());
		return json;
	}

	@Override
	public JSONObject updateOne(String message) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(tipMapper.updateOne(message)>0) {
			json.put("info", "update success");
			json.put("result", 1);
		}else {
			json.put("error", "the sql error");
			json.put("result", 0);
		}
		return json;
	}

}
