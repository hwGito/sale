package com.sale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.mapper.UserMapper;
import com.sale.pojo.User;
import com.sale.service.UserService;

import net.sf.json.JSONObject;

@Service
public class UserServiceImpl implements UserService{

	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User loginck(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.loginck(username,password);
	}

	@Override
	public JSONObject findById(String u_id) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		User user = userMapper.findById(u_id);
		user.setU_pwd("");
		json.put("user", user);
		return json;
	}

	@Override
	public JSONObject updateOne(User user) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(userMapper.updateOne(user)>0) {
			json.put("info", "update success");
			json.put("result", 1);
		}else {
			json.put("error", "sql error");
			json.put("result", 0);
		}
		return json;
	}

	@Override
	public JSONObject updatePwd(String u_id, String u_acount, String u_pwd, String o_pwd) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		User u = userMapper.loginck(u_acount, o_pwd);
		if(u!=null) {
			if(userMapper.updatePwd(u_id, u_pwd)>0) {
				json.put("info", "update success");
				json.put("result", 1);
			}else {
				json.put("error", "sql error");
				json.put("result", 0);
			}
		}else {
			json.put("error", "the old password is wrong");
		}
		return json;
	}



	


}
