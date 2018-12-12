package com.xm.testaction.qualitycheck.orderSum;

public class BeanCustomerOrders {
	private String orderId;
	private String manuCost;	//制造成本
	private String allCost;		//总成本
	private String orderStatus;	//订单状态
	private String orderVal;	//订单总价
	
	private String profit;		//利润
	private String profitRate;	// 利润率
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getManuCost() {
		return manuCost;
	}
	public void setManuCost(String manuCost) {
		this.manuCost = manuCost;
	}
	public String getAllCost() {
		return allCost;
	}
	public void setAllCost(String allCost) {
		this.allCost = allCost;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderVal() {
		return orderVal;
	}
	public void setOrderVal(String orderVal) {
		this.orderVal = orderVal;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getProfitRate() {
		return profitRate;
	}
	public void setProfitRate(String profitRate) {
		this.profitRate = profitRate;
	}
}
