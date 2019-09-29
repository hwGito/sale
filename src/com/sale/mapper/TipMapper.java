package com.sale.mapper;

import org.apache.ibatis.annotations.Param;

import com.sale.pojo.Tip;

public interface TipMapper {

	Tip getTip();

	int updateOne(@Param("message")String message);

}
