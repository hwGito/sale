package com.sale.pojo;

public class Bill {
	private int b_id;
	private String b_number;
	private String b_time;
	private String c_name;
	private String c_phone;
	private String c_plate;
	private int b_status;
	private String u_name;
	private int b_payway;
	private double b_total;
	private double b_real;
	private String c_add;
	
	public double getB_real() {
		return b_real;
	}
	public void setB_real(double b_real) {
		this.b_real = b_real;
	}
	
	public String getC_add() {
		return c_add;
	}
	public void setC_add(String c_add) {
		this.c_add = c_add;
	}
	public double getB_total() {
		return b_total;
	}
	public void setB_total(double b_total) {
		this.b_total = b_total;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_number() {
		return b_number;
	}
	public void setB_number(String b_number) {
		this.b_number = b_number;
	}
	public String getB_time() {
		return b_time;
	}
	public void setB_time(String b_time) {
		this.b_time = b_time;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public String getC_plate() {
		return c_plate;
	}
	public void setC_plate(String c_plate) {
		this.c_plate = c_plate;
	}
	public int getB_status() {
		return b_status;
	}
	public void setB_status(int b_status) {
		this.b_status = b_status;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public int getB_payway() {
		return b_payway;
	}
	public void setB_payway(int b_payway) {
		this.b_payway = b_payway;
	}
	
	public GoodsBill extendGoodsBill() {
		GoodsBill goodsBill = new GoodsBill();
		goodsBill.setB_id(b_id);
		goodsBill.setB_number(b_number);
		goodsBill.setB_payway(b_payway);
		goodsBill.setB_status(b_status);
		goodsBill.setB_time(b_time);
		goodsBill.setB_total(b_total);
		goodsBill.setC_name(c_name);
		goodsBill.setC_phone(c_phone);
		goodsBill.setC_plate(c_plate);
		goodsBill.setU_name(u_name);
		goodsBill.setB_real(b_real);
		goodsBill.setC_add(c_add);
		return goodsBill;
	}
}
