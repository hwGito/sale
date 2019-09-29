package com.sale.controller.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sale.pojo.Bill;
import com.sale.pojo.BillExtend;
import com.sale.service.BillService;
import com.sale.utils.LogUtil;
import com.sale.utils.ResponseJSON;
import com.sale.utils.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@CrossOrigin
@Controller
@RequestMapping("/main")
public class BillController {
	
	private BillService billService;
	
//	private Logger log = Logger.getLogger(BillController.class);
	
	public BillService getBillService() {
		return billService;
	}
	@Autowired
	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	/**
	 * 分页查询所有账单
	 * @return
	 */
	@RequestMapping("/billfa")
	public ModelAndView findAllBill(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		if(page!=null && size!=null && !"".equals(page) && !"".equals(size)) {
			String b_status = request.getParameter("bstatus");
			String c_name = request.getParameter("cname");
			String b_time = request.getParameter("btime");
			String u_name = request.getParameter("uname");
			String c_plate = request.getParameter("cplate");
			
			Map<String, Object> map = new HashMap<>();
			map.put("b_status", b_status);
			map.put("c_name", c_name);
			map.put("b_time", b_time);
			map.put("u_name", u_name);
			map.put("c_plate", c_plate);
			if(b_time!=null && !"".equals(b_time)) {
				long btimel = Long.parseLong(b_time);
				long b_time_end = btimel + 86400l;
				map.put("b_time_end", b_time_end);
			}

/**/		ResponseJSON.formatJSON(0, billService.findAll(Integer.parseInt(page),Integer.parseInt(size),map), "success", mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/billfa");
		}
		return mav;
	}
	

	/**
	 * 插入账单信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/billin")
	public ModelAndView insertBill(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String c_name = request.getParameter("cname");
		String c_phone = request.getParameter("cphone");
		String c_plate = request.getParameter("cplate");
		String b_status = request.getParameter("bstatus");
		String b_payway = request.getParameter("bpayway");
		String u_name = request.getParameter("uname");
		String b_total = request.getParameter("btotal");
		String b_real = request.getParameter("breal");
		String c_add = request.getParameter("cadd");
		String goodsList = request.getParameter("goodslist");
		if(c_name!=null && c_phone!=null && c_plate!=null && b_status!=null && b_payway!=null && u_name!=null && b_real !=null &&
				b_total !=null && goodsList !=null && !"".equals(c_name) && !"".equals(c_phone) && !"".equals(c_plate) && !"".equals(b_status)
				&& !"".equals(b_payway) && !"".equals(u_name) && !"".equals(b_total) && !"".equals(goodsList) &&!"".equals(b_real)) {
			
			String timeState = TimeUtil.getTimeState();
			String b_number = TimeUtil.number5AndTime8(timeState);
			
/**/		Bill bill = this.setupBill("",b_number,b_payway,b_status,timeState,c_name,c_phone,u_name,c_plate,b_total,b_real,c_add);

			
			JSONObject json = JSONObject.fromObject(goodsList);
		
			if(json.has("list")) {
/**/			List<BillExtend> list = this.parseGoodsListJSON(json,b_number);
				JSONObject datas = billService.insertOne(bill,list);
/**/			this.setUpdateData(datas,mav);
			}else {
/**/			ResponseJSON.formatJSON(1,"","JSON is wrong",mav);
				LogUtil.error("failure,JSON is wrong","/main/billin");
			}
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/billin");
		}
		
		return mav;
	}
	
	/**
	 * 账单修改
	 * @param request
	 * @return
	 */
	@RequestMapping("/billup")
	public ModelAndView updateBill(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String c_name = request.getParameter("cname");
		String b_id = request.getParameter("bid");
		String b_number = request.getParameter("bnumber");
		String c_phone = request.getParameter("cphone");
		String c_plate = request.getParameter("cplate");
		String b_status = request.getParameter("bstatus");
		String b_payway = request.getParameter("bpayway");
		String b_real = request.getParameter("breal");
		String c_add = request.getParameter("cadd");
		String goodsList = request.getParameter("goodslist");
		if(c_name!=null && b_id!=null && c_phone!=null && c_plate!=null && b_status!=null && b_payway!=null && b_real!=null && goodsList !=null &&
			b_number!=null &&	!"".equals(c_name) && !"".equals(b_id) && !"".equals(c_phone) && !"".equals(c_plate) && !"".equals(b_status) && !"".equals(b_payway)
				 && !"".equals(b_real) && !"".equals(goodsList) && !"".equals(b_number)) {

/**/		Bill bill = this.setupBill(b_id,b_number, b_payway, b_status, "", c_name, c_phone, "", c_plate, "", b_real, c_add);
			
			JSONObject json = JSONObject.fromObject(goodsList);
			
			if(json.has("list")) {
				List<BillExtend> list = this.parseGoodsListJSON(json, b_number);	
				
/**/			JSONObject datas = billService.updateOne(bill,list);
				
/**/			this.setUpdateData(datas, mav);
			}else {
/**/			ResponseJSON.formatJSON(1, "", "JSON is wrong", mav);
				LogUtil.error("failure,JSON is wrong","/main/billin");
			}
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/billup");
		}
		return mav;
	}
	/**
	 * 删除账单
	 * @param request
	 * @return
	 */
	@RequestMapping("/billde")
	public ModelAndView deleteBill(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String b_id = request.getParameter("bid");
		if(b_id!=null && !"".equals(b_id)) {
			JSONObject datas = billService.deleteById(b_id);
/**/		this.setUpdateData(datas, mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/billde");
		}
		return mav;
	}
	/**
	 * 按账单id查询账单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/billdetailfi")
	public ModelAndView findBillDetailById(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		String bid = request.getParameter("bid");
		if(bid!=null && !"".equals(bid)) {
/**/		ResponseJSON.formatJSON(0, billService.findDetailById(Integer.parseInt(bid)), "success", mav);
		}else {
/**/		ResponseJSON.formatJSON(1, "", "failure,one of parameters is null", mav);
			LogUtil.error("failure,one of parameters is null","/main/billdetailfi");
		}
		return mav;
	}
	
	/**
	 * 设置bill bean
	 * @param b_number
	 * @param b_payway
	 * @param b_status
	 * @param timeState
	 * @param c_name
	 * @param c_phone
	 * @param u_name
	 * @param c_plate
	 * @param b_total
	 * @param b_real
	 * @param c_add 
	 * @return
	 */
	private Bill setupBill(String b_id,String b_number, String b_payway, String b_status, String timeState, String c_name,
			String c_phone, String u_name, String c_plate, String b_total, String b_real, String c_add) {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		if(b_id!=null && !"".equals(b_id)) {
			bill.setB_id(Integer.parseInt(b_id));
		}
		bill.setB_number(b_number);
		if(b_payway!=null && !"".equals(b_payway)) {
			bill.setB_payway(Integer.parseInt(b_payway));
		}
		if(b_status!=null && !"".equals(b_status)) {
			bill.setB_status(Integer.parseInt(b_status));
		}
		if(timeState!=null && !"".equals(timeState))
			bill.setB_time(timeState);
		if(c_name!=null && !"".equals(c_name))
			bill.setC_name(c_name);
		if(c_phone!=null && !"".equals(c_phone))
			bill.setC_phone(c_phone);
		if(u_name!=null && !"".equals(u_name))
			bill.setU_name(u_name);
		if(c_plate !=null && !"".equals(c_plate))
			bill.setC_plate(c_plate);
		if(b_total!=null && !"".equals(b_total)) {
			bill.setB_total(Double.parseDouble(b_total));
		}
		if(b_real!=null && !"".equals(b_real)) {
			bill.setB_real(Double.parseDouble(b_real));
		}
		if(c_add!=null && !"".equals(c_add)) {
			bill.setC_add(c_add);
		}
		return bill;
	}
	
	/**
	 * 解析goodslist
	 * @param json fromObject（goodsList）
	 * @param b_number 关联账单编号
	 * @return
	 */
	private List<BillExtend> parseGoodsListJSON(JSONObject json, String b_number) {
		// TODO Auto-generated method stub
		List<BillExtend> list = new ArrayList<>();
		JSONArray jsa = json.getJSONArray("list");
		for(int i = 0;i<jsa.size();i++) {
			BillExtend be = new BillExtend();
			be.setB_number(b_number);
			be.setG_id(Integer.parseInt(jsa.getJSONObject(i).getString("gid")));
			be.setGb_count(Integer.parseInt(jsa.getJSONObject(i).getString("gbcount")));
			list.add(be);
		}
		return list;
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
