package com.sale.controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sale.pojo.User;
import com.sale.service.UserService;
import com.sale.utils.LogUtil;
import com.sale.utils.ResponseJSON;

import net.sf.json.JSONObject;

@CrossOrigin
@Controller
@RequestMapping("/main")
public class UserController {
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 根据个人用户id查询个人用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/userfi")
	public ModelAndView findById(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String u_id = request.getParameter("uid");
		if(u_id!=null && !"".equals(u_id)) {
/**/		ResponseJSON.formatJSON(0, userService.findById(u_id), "success", mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/userfi");
		}
		return mav;
	}
	
	/**
	 * 用户修改个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/userup")
	public ModelAndView updateById(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String u_name = request.getParameter("uname");
		String u_id = request.getParameter("uid");
		String u_phone = request.getParameter("uphone");
		if(u_name!=null && u_id!=null && u_phone!=null &&
				!"".equals(u_name) && !"".equals(u_id) && !"".equals(u_phone)) {
/**/		User user = this.setupUser(u_id, "", 0, u_name, u_phone, "");
			
			JSONObject datas = userService.updateOne(user);
/**/		this.setUpdateData(datas, mav);
			
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/userup");
		}
		return mav;
	}
	
	@RequestMapping("/userupwd")
	public ModelAndView updatePwd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String u_id = request.getParameter("uid");
		String u_acount = request.getParameter("uacount");
		String u_pwd = request.getParameter("npwd");
		String o_pwd = request.getParameter("opwd");
		if(u_id!=null && u_pwd!=null && o_pwd!=null && u_acount!=null &&
				!"".equals(u_id) && !"".equals(u_pwd) && !"".equals(o_pwd) &&!"".equals(u_acount)) {
			JSONObject datas = userService.updatePwd(u_id,u_acount,u_pwd,o_pwd);
			
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/userupwd");
		}
		return mav;
	}
	
	/**
	 * 设置修改型接口data返回参数
	 * @param datas 需要返回的data
	 * @param mav 模块
	 */
	private void setUpdateData(JSONObject datas, ModelAndView mav) {
		// TODO Auto-generated method stub
		if(datas.has("error")) {
			mav.addObject("code",1);
			mav.addObject("message",datas.get("error"));
		}else {
			mav.addObject("code",0);
			mav.addObject("message",datas.get("info"));
		}
		mav.addObject("data",datas);
	}
	/**
	 * 设置User bean
	 * @param u_id
	 * @param u_acount
	 * @param u_level
	 * @param u_name
	 * @param u_phone
	 * @param u_pwd
	 * @return
	 */
	private User setupUser( String u_id,String u_acount, int u_level, String u_name, String u_phone, String u_pwd) {
		// TODO Auto-generated method stub
		User user = new User();
		if(u_id!=null && !"".equals(u_id)) {
			user.setU_id(Integer.parseInt(u_id));
		}
		user.setU_level(u_level);
		if(u_name!=null && !"".equals(u_name)) {
			user.setU_name(u_name);
		}
		if(u_phone!=null && !"".equals(u_phone)) {
			user.setU_phone(u_phone);
		}
		if(u_acount!=null && !"".equals(u_acount)) {
			user.setU_acount(u_acount);
		}
		if(u_pwd!=null && !"".equals(u_pwd)) {
			user.setU_pwd(u_pwd);
		}
		return user;
	}
}
