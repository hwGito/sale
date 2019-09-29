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

import com.sale.pojo.Goods;
import com.sale.service.GoodsService;
import com.sale.utils.LogUtil;
import com.sale.utils.ResponseJSON;
import com.sale.utils.TimeUtil;

import net.sf.json.JSONObject;

@CrossOrigin
@Controller
@RequestMapping("/main")
public class GoodsController {
	
	
	private GoodsService goodsService;

	public GoodsService getGoodsService() {
		return goodsService;
	}

	@Autowired
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	/**
	 * 分页分类查询商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsfa")
	public ModelAndView findAllByType(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		if(page!=null && size!=null && !"".equals(page) && !"".equals(size)) {
			String g_name = request.getParameter("gname");
			String g_number = request.getParameter("gnumber");
			String g_status = request.getParameter("gstatus");
			String g_intime = request.getParameter("gintime");
			
			Map<String, Object> map = new HashMap<>();
			map.put("g_name", g_name);
			map.put("g_number", g_number);
			map.put("g_status", g_status);
			map.put("g_intime", g_intime);
			if(g_intime!=null && !"".equals(g_intime)) {
				long g_intime_l = Long.parseLong(g_intime);
				long g_intime_end = g_intime_l + 86400l;
				map.put("g_intime_end", g_intime_end);
			}
			

/**/		ResponseJSON.formatJSON(0, goodsService.findAll(Integer.parseInt(page), Integer.parseInt(size),map), "success", mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/goodsfa");
		}
		return mav;
	}
	
	/**
	 * 根据id查询商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsfi")
	public ModelAndView findGoodsById(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String g_id = request.getParameter("gid");
		if(g_id!=null && !"".equals(g_id)) {

/**/		ResponseJSON.formatJSON(0, goodsService.findById(g_id), "success", mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/goodsfi");
		}
		return mav;
	}
	
	/**
	 * 新增商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsin")
	public ModelAndView Addgoods(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String g_name = request.getParameter("gname");
		String g_number = request.getParameter("gnumber");
		String g_price = request.getParameter("gprice");
		String g_desc = request.getParameter("gdesc");
		String g_count = request.getParameter("gcount");
		String g_status = request.getParameter("gstatus");
		String g_inprice = request.getParameter("ginprice");
		
		if(g_name!=null && g_number!=null && g_price!=null && g_desc!=null && g_count!=null && g_status!=null && g_inprice!=null &&
			!"".equals(g_name) && !"".equals(g_number) &&!"".equals(g_price) &&!"".equals(g_desc) && !"".equals(g_count) &&
			!"".equals(g_status) && !"".equals(g_inprice)) {
			
			String g_intime = TimeUtil.getTimeState();
		
/**/		Goods goods = this.setupGoods(g_count, g_desc, "", g_inprice, g_intime, g_name, g_number, g_price, g_status);
								
			JSONObject datas = goodsService.insertOne(goods);
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/goodsin");
		}
		return mav;
	}
	/**
	 * 修改商品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsup")
	public ModelAndView updateGoods(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String g_name = request.getParameter("gname");
		String g_number = request.getParameter("gnumber");
		String g_price = request.getParameter("gprice");
		String g_desc = request.getParameter("gdesc");
		String g_count = request.getParameter("gcount");
		String g_intime = request.getParameter("gintime");
		String g_status = request.getParameter("gstatus");
		String g_inprice = request.getParameter("ginprice");
		String g_id = request.getParameter("gid");
		if(g_name!=null && g_number!=null && g_price!=null && g_desc!=null && g_count!=null && g_intime!=null && g_status!=null && g_inprice!=null && g_id!=null &&
				!"".equals(g_name) && !"".equals(g_number) && !"".equals(g_price) && !"".equals(g_desc) && !"".equals(g_count) && !"".equals(g_intime) &&
				!"".equals(g_status) && !"".equals(g_inprice) && !"".equals(g_id)) {
/**/		Goods goods = this.setupGoods(g_count, g_desc, g_id, g_inprice, g_intime, g_name, g_number, g_price, g_status);
		
			JSONObject datas = goodsService.updateOne(goods);
			
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/goodsin");
		}
		return mav;
	}
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsde")
	public ModelAndView deleteGoods(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String g_id = request.getParameter("gid");
		if(g_id!=null && !"".equals(g_id)) {
			JSONObject datas = goodsService.deleteOne(g_id);
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/goodsin");
		}
		return mav;
	}
	/**
	 * 设置Goods bean
	 * @param g_count
	 * @param g_desc
	 * @param g_id
	 * @param g_inprice
	 * @param g_intime
	 * @param g_name
	 * @param g_number
	 * @param g_price
	 * @param g_status
	 * @return
	 */
	private Goods setupGoods(String g_count,String g_desc,String g_id,String g_inprice,String g_intime,String g_name,String g_number,String g_price,String g_status) {
		Goods goods = new Goods();
		if(g_count!=null && !"".equals(g_count))
			goods.setG_count(Integer.parseInt(g_count));
		if(g_desc!=null && !"".equals(g_desc))
			goods.setG_desc(g_desc);
		if(g_id!=null && !"".equals(g_id))
			goods.setG_id(Integer.parseInt(g_id));
		if(g_inprice != null && !"".equals(g_inprice))
			goods.setG_inprice(Double.parseDouble(g_inprice));
		if(g_intime!=null && !"".equals(g_intime))
			goods.setG_intime(g_intime);
		if(g_name!=null && !"".equals(g_name))
			goods.setG_name(g_name);
		if(g_number!=null && !"".equals(g_number))
			goods.setG_number(g_number);
		if(g_price!=null && !"".equals(g_price))
			goods.setG_price(Double.parseDouble(g_price));
		if(g_status!=null && !"".equals(g_status))
			goods.setG_status(Integer.parseInt(g_status));
		return goods;
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
