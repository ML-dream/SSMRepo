package com.wl.forms;

import java.sql.Date;

public class StationPlan {
	private String orderid ;
	private String productid;
	private int aono;
	private int num;
	private Date starttime;
	private Date endtime;
	private int placeid;
	private String placename;
	private String  planperson;
	private Date plantime;
	private String qualityid;
	private String barcode;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public int getAono() {
		return aono;
	}
	public void setAono(int aono) {
		this.aono = aono;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getPlaceid() {
		return placeid;
	}
	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public String getPlanperson() {
		return planperson;
	}
	public void setPlanperson(String planperson) {
		this.planperson = planperson;
	}
	public Date getPlantime() {
		return plantime;
	}
	public void setPlantime(Date plantime) {
		this.plantime = plantime;
	}
	public String getQualityid() {
		return qualityid;
	}
	public void setQualityid(String qualityid) {
		this.qualityid = qualityid;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
