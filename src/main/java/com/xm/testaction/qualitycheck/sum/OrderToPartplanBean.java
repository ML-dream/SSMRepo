package com.xm.testaction.qualitycheck.sum;

public class OrderToPartplanBean {
	private String orderId;
	
	private String partsplanid;
	private String plantime;	//制定计划的时间
	private String product_name;
	private String product_id;	
	private String pstate;		//零件完成状态
	private int partnum;	//零件数量
	private String order_id;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPartsplanid() {
		return partsplanid;
	}
	public void setPartsplanid(String partsplanid) {
		this.partsplanid = partsplanid;
	}
	public String getPlantime() {
		return plantime;
	}
	public void setPlantime(String plantime) {
		this.plantime = plantime;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String productName) {
		product_name = productName;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String productId) {
		product_id = productId;
	}
	public String getPstate() {
		return pstate;
	}
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}
	public int getPartnum() {
		return partnum;
	}
	public void setPartnum(int partnum) {
		this.partnum = partnum;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		order_id = orderId;
	}
	
}
