package com.sale.service;

import java.util.List;
import java.util.Map;

import com.sale.pojo.Bill;
import com.sale.pojo.BillExtend;

import net.sf.json.JSONObject;

public interface BillService {
	/**
	 * 分页查询所有
	 * @param page
	 * @param size
	 * @param map参数
	 * @return
	 */
	public JSONObject findAll(int page,int size, Map<String, Object> map);


	/**
	 * 插入一条数据
	 * @param bill
	 * @param list 
	 * @return
	 */
	public JSONObject insertOne(Bill bill, List<BillExtend> list);

	/**
	 * 修改一条数据
	 * @param bill
	 * @param list 
	 * @return
	 */
	public JSONObject updateOne(Bill bill, List<BillExtend> list);

	/**
	 * 删除一条数据
	 * @param b_id
	 * @return
	 */
	public JSONObject deleteOne(String b_id);
	/**
	 * 按账单id查询账单详情
	 * @param parseInt
	 * @return
	 */
	public JSONObject findDetailById(int b_id);

	/**
	 * 按id删除一条数据
	 * @param b_id
	 * @return
	 */
	public JSONObject deleteById(String b_id);


	

	
}
