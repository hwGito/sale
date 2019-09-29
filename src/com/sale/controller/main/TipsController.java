package com.sale.controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sale.service.TipService;
import com.sale.utils.LogUtil;
import com.sale.utils.ResponseJSON;

import net.sf.json.JSONObject;

@CrossOrigin
@Controller
@RequestMapping("/main")
public class TipsController {
	private TipService tipService;

	public TipService getTipService() {
		return tipService;
	}

	@Autowired
	public void setTipService(TipService tipService) {
		this.tipService = tipService;
	}
	/**
	 * 查找公告
	 * @return
	 */
	@RequestMapping("/tipfa")
	public ModelAndView getTip() {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
/**/	ResponseJSON.formatJSON(0, tipService.getTip(), "success", mav);
		return mav;
	}
	
	/**
	 * 修改公告
	 * @param request
	 * @return
	 */
	@RequestMapping("/tipup")
	public ModelAndView updateTip(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String message = request.getParameter("message");
		if(message!=null && !"".equals(message)) {
			JSONObject datas = tipService.updateOne(message);
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(0, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/tipup");
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
}
