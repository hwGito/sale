package com.sale.pojo;

import java.util.List;

public class GoodsBill extends Bill{
	private List<GoodsUnio> goodslist;

	public List<GoodsUnio> getGoodslist() {
		return goodslist;
	}

	public void setGoodslist(List<GoodsUnio> goodslist) {
		this.goodslist = goodslist;
	}
}
