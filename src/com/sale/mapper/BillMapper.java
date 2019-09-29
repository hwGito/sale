package com.sale.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sale.pojo.Bill;
import com.sale.pojo.BillExtend;
import com.sale.pojo.GoodsUnio;

public interface BillMapper {
	List<Bill> findAll(@Param("start") int start,@Param("size") int size,@Param("map") Map<String, Object> map);

	Bill findById(@Param(value = "b_id") int b_id);

	int insertOne(Bill bill);

	int updateOne(Bill bill);

	int deleteOne(@Param("b_number") String b_number);

	List<GoodsUnio> findGoodslist(@Param("b_number") String b_number);

	int countAll(@Param("start") int start,@Param("size") int size,@Param("map") Map<String, Object> map);

	int insertBillEx(@Param("list") List<BillExtend> list);

	int deleteBillEx(@Param("b_number")String b_number);

	int deleteById(@Param("b_id") String b_id);

	double findPriceById(@Param("g_id")int g_id);

}
