package com.sale.controller.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sale.pojo.User;
import com.sale.service.AdminService;
import com.sale.utils.LogUtil;
import com.sale.utils.ResponseJSON;

import net.sf.json.JSONObject;

@CrossOrigin
@Controller
@RequestMapping("/main")
public class AdminController {
	
	private AdminService adminService;
	
	public AdminService getAdminService() {
		return adminService;
	}


	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * 分页分类查询用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminfa")
	public ModelAndView findAllUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		if(page != null && size !=null && !"".equals(page) && !"".equals(size)) {
			String u_name = request.getParameter("uname");
			String u_level = request.getParameter("ulevel");
			String u_phone = request.getParameter("uphone");
			
			Map<String, Object> map = new HashMap<>();
			map.put("u_name", u_name);
			map.put("u_level", u_level);
			map.put("u_phone", u_phone);
			
/**/		ResponseJSON.formatJSON(0, adminService.findAllUser(Integer.parseInt(page), Integer.parseInt(size),map), "success", mav);
		}else {
/**/		ResponseJSON.formatJSON(1,"","failure,one of parameters is null",mav);
			LogUtil.error("failure,one of parameters is null","/main/adminfa");
		}
		return mav;
	}
	/**
	 * 新增用户操作员
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminin")
	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String u_name = request.getParameter("uname");
		String u_acount = request.getParameter("uacount");
		String u_pwd = request.getParameter("upwd");
		int u_level = 0;
		String u_phone = request.getParameter("uphone");
		if(u_name!=null && u_acount!=null && u_pwd!=null && u_phone!=null &&
				!"".equals(u_name) && !"".equals(u_acount) && !"".equals(u_pwd) && !"".equals(u_phone)) {
			
/**/		User user = this.setupUser("",u_acount,u_level,u_name,u_phone,u_pwd);
			
			JSONObject datas = adminService.insertOne(user);

/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/adminin");
		}	
		return mav;
	}

	/**
	 * 管理员修改用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminup")
	public ModelAndView updateUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String u_id = request.getParameter("tarid");
		String u_phone = request.getParameter("uphone");
		String u_acount = request.getParameter("uacount");
		String u_pwd = request.getParameter("upwd");
		String u_name = request.getParameter("uname");
		if(u_id!=null && u_phone!=null && u_name!=null && !"".equals(u_id) && !"".equals(u_phone) && !"".equals(u_name)) {
/**/		User user = this.setupUser(u_id,u_acount, 0, u_name, u_phone, u_pwd);

			JSONObject datas = adminService.updateOne(user);
			
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/adminup");
		}
		return mav;
	}
	/**
	 * 删除一个用户（操作员）
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminde")
	public ModelAndView deleteUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String u_id = request.getParameter("tarid");
		if(u_id!=null && !"".equals(u_id)) {
			JSONObject datas = adminService.deleteOne(u_id);
			
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/adminde");
		}
		return mav;
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
}
