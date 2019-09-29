package com.sale.service;

import com.sale.pojo.User;

import net.sf.json.JSONObject;

public interface UserService {

	public User loginck(String username, String password);

	public JSONObject findById(String u_id);

	public JSONObject updateOne(User user);

	public JSONObject updatePwd(String u_id, String u_acount, String u_pwd, String o_pwd);


}
