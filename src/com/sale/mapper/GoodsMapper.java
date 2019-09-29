package com.sale.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sale.pojo.Goods;

public interface GoodsMapper {


	Goods findById(@Param("g_id")String g_id);

	int insertOne(Goods goods);

	int updateOne(Goods goods);

	int deleteOne(@Param("g_id") String g_id);

	int countAll(@Param("map")Map<String, Object> map);

	List<Goods> findAll(@Param("start") int start,@Param("size") int size,@Param("map") Map<String, Object> map);

}
