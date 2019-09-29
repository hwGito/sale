package com.sale.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sale.service.CommenService;
import com.sale.utils.ResponseJSON;

@Controller
@CrossOrigin
@RequestMapping("/main")
public class CommenController {
	private CommenService commenService;
	
	public CommenService getCommenService() {
		return commenService;
	}

	@Autowired
	public void setCommenService(CommenService commenService) {
		this.commenService = commenService;
	}
	/**
	 * 计算总销售额
	 * @return
	 */
	@RequestMapping("/caltotal")
	public ModelAndView calTotal() {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
/**/	ResponseJSON.formatJSON(0, commenService.calTotal(), "success", mav);
		return mav;
	}
}
