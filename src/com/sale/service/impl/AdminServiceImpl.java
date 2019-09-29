package com.sale.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.mapper.UserMapper;
import com.sale.pojo.User;
import com.sale.service.AdminService;

import net.sf.json.JSONObject;

@Service
public class AdminServiceImpl implements AdminService{

	private UserMapper userMapper;
	
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public JSONObject findAllUser(int page, int size, Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		int start = (page - 1) * size;
		List<User> list = userMapper.findAllUser(start, size,map);
		for(int i =0;i<list.size();i++) {
			list.get(i).setU_pwd("");
		}
		json.put("total", userMapper.countAllUser(map));
		json.put("list", list);
		return json;
	}

	@Override
	public JSONObject insertOne(User user) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(userMapper.insertOne(user)>0) {
			json.put("info", "insert success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

	@Override
	public JSONObject updateOne(User user) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(userMapper.adminUpdateOne(user)>0) {
			json.put("info", "update success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

	@Override
	public JSONObject deleteOne(String u_id) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(userMapper.adminDeleteOne(u_id)>0) {
			json.put("info", "delete success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

}
