package com.sale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.mapper.CommenMapper;
import com.sale.service.CommenService;

import net.sf.json.JSONObject;

@Service
public class CommenServiceImpl implements CommenService{

	private CommenMapper commenMapper;
	
	public CommenMapper getCommenMapper() {
		return commenMapper;
	}

	@Autowired
	public void setCommenMapper(CommenMapper commenMapper) {
		this.commenMapper = commenMapper;
	}

	@Override
	public JSONObject calTotal() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("total", commenMapper.calTotal());
		return json;
	}

}
