package com.sale.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.mapper.BillMapper;
import com.sale.mapper.GoodsMapper;
import com.sale.pojo.Bill;
import com.sale.pojo.BillExtend;
import com.sale.pojo.Goods;
import com.sale.pojo.GoodsBill;
import com.sale.pojo.GoodsUnio;
import com.sale.service.BillService;

import net.sf.json.JSONObject;

@Service
public class BillServiceImpl implements BillService{
	
	private BillMapper billMapper;
	
	private GoodsMapper goodsMapper;
	
	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}
	@Autowired
	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}
	public BillMapper getBillMapper() {
		return billMapper;
	}
	@Autowired
	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}
	
	
	@Override
	public JSONObject deleteOne(String b_number) {//删除一条数据
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(billMapper.deleteOne(b_number)>0 && billMapper.deleteBillEx(b_number) > 0) {
			json.put("info","delete success");
			json.put("result", 1);
		}else {
			json.put("error", "failure, sql error");
			json.put("result", 0);
		}
		return json;
	}
	@Override
	public JSONObject findDetailById(int b_id) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		Bill bill = billMapper.findById(b_id);
		GoodsBill goodsBill = bill.extendGoodsBill();
		List<GoodsUnio> goodslist = billMapper.findGoodslist(goodsBill.getB_number());
		goodsBill.setGoodslist(goodslist);
		json.put("bill", goodsBill);
		return json;
	}
	@Override
	public JSONObject findAll(int page, int size, Map<String, Object> map) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		int start = (page-1) * size;
		json.put("total",billMapper.countAll(start, size,map));
		json.put("list", billMapper.findAll(start, size,map));
		return json;
	}
	@Override
	public JSONObject insertOne(Bill bill, List<BillExtend> list) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(!list.isEmpty()) {
			if(billMapper.insertOne(bill)>0 && billMapper.insertBillEx(list) > 0) {
				for(int i = 0;i<list.size();i++) {
					Goods goods = goodsMapper.findById(String.valueOf(list.get(i).getG_id()));
					if(goods.getG_count()>0) {
						int count = goods.getG_count();
						count = count - list.get(i).getGb_count();
						goods.setG_count(count);
						goodsMapper.updateOne(goods);
					}
				}
				json.put("info","insert success");
				json.put("result", 1);
			}else {
				json.put("error", "failure, sql error");
				json.put("result", 0);
			}
		}else {
			if(billMapper.insertOne(bill)>0) {
				json.put("info","insert success");
				json.put("result", 1);
			}else {
				json.put("error", "failure, sql error");
				json.put("result", 0);
			}
		}
		return json;
	}
	@Override
	public JSONObject updateOne(Bill bill,List<BillExtend> list) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		String b_number = bill.getB_number();
		List<GoodsUnio> olist = billMapper.findGoodslist(b_number);
		if(!olist.isEmpty()) {
			for(int i = 0;i<olist.size();i++) {
				Goods goods = olist.get(i);
				if(goods.getG_count()>0) {
					int count = goods.getG_count();
					count = count + olist.get(i).getGb_count();
					goods.setG_count(count);
					goodsMapper.updateOne(goods);
				}
			}
			billMapper.deleteBillEx(b_number);	
		}
		
		double total = 0.0;
		if(!list.isEmpty()) {
			for(int i = 0;i<list.size();i++) {
				Goods goods = goodsMapper.findById(String.valueOf(list.get(i).getG_id()));
				total = total + goods.getG_price() * list.get(i).getGb_count();
				if(goods.getG_count()>0) {
					int count = goods.getG_count();
					count = count - list.get(i).getGb_count();
					goods.setG_count(count);
					goodsMapper.updateOne(goods);
				}
			}
			
			billMapper.insertBillEx(list);
			
			bill.setB_total(total);
		}
		
		if(billMapper.updateOne(bill)>0) {
			json.put("info","update success");
			json.put("result", 1);
		}else {
			json.put("error", "failure, sql error");
			json.put("result", 0);
		}
		return json;
	}
	@Override
	public JSONObject deleteById(String b_id) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(billMapper.deleteById(b_id)>0) {
			json.put("info","delete success");
			json.put("result", 1);
		}else {
			json.put("error", "failure, sql error");
			json.put("result", 0);
		}
		return json;
	}
	

}
