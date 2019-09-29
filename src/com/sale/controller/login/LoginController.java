package com.sale.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sale.pojo.User;
import com.sale.service.UserService;
import com.sale.utils.Constant;
import com.sale.utils.LogUtil;
import com.sale.utils.RandomUtil;
import com.sale.utils.TokenUtil;

import net.sf.json.JSONObject;

@CrossOrigin()
@Controller
public class LoginController {
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 登录检查接口
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/loginck")
	@ResponseBody
	public ModelAndView loginck(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null && password!=null) {
			JSONObject data = new JSONObject();
			JSONObject subjectjson = new JSONObject();
			User user = userService.loginck(username,password);
			if(user != null) {
				user.setU_pwd("");
				mav.addObject("code",0);
				mav.addObject("message","success");
				data.put("user",user);
				subjectjson.put("u_id", user.getU_id());
				subjectjson.put("username", username);
				data.put("token", TokenUtil.createToken(RandomUtil.RandomNum10(), subjectjson.toString(),new Long(Constant.JWT_EXP)));
				mav.addObject("data",data);
			}else {
				mav.addObject("code",1);
				mav.addObject("message","the password is wrong");
				mav.addObject("data","");
			}
		}else {
			mav.addObject("code",1);
			mav.addObject("data","");
			mav.addObject("message","failure,one of parameters is null");
			LogUtil.error("failure,one of parameters is null","/loginck");
		}
		
		return mav;
	}
	
}
