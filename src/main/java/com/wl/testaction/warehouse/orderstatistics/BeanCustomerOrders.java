package com.wl.testaction.warehouse.orderstatistics;

public class BeanCustomerOrders {
	private String orderid;
	private String productid;
	private double manuc;	//制造成本
	private double allc;	//总成本
	private double profit;	//利润
	private String profitrate;	//利润率
	private double orderprice;	//订单总价
	private String orderStatus;	//状态
	private String productName;	//产品名称
	private String productStatus;	//产品状态
	private double productprice;	//产品总价
	private int productNum ;
	private double qualityLoss;		//质量损失
	
	private String Smanuc;	//合计
	public String getSmanuc() {
		return Smanuc;
	}
	public void setSmanuc(String smanuc) {
		Smanuc = smanuc;
	}
	public String getSallc() {
		return Sallc;
	}
	public void setSallc(String sallc) {
		Sallc = sallc;
	}
	public String getSprofit() {
		return Sprofit;
	}
	public void setSprofit(String sprofit) {
		Sprofit = sprofit;
	}
	public String getSorderprice() {
		return Sorderprice;
	}
	public void setSorderprice(String sorderprice) {
		Sorderprice = sorderprice;
	}
	public String getSqualityLoss() {
		return SqualityLoss;
	}
	public void setSqualityLoss(String squalityLoss) {
		SqualityLoss = squalityLoss;
	}
	private String Sallc;
	private String Sprofit;
	private String Sorderprice;
	private String SqualityLoss;
	
	public double getQualityLoss() {
		return qualityLoss;
	}
	public void setQualityLoss(double qualityLoss) {
		this.qualityLoss = qualityLoss;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public double getProductprice() {
		return productprice;
	}
	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(double orderprice) {
		this.orderprice = orderprice;
	}
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
	public double getManuc() {
		return manuc;
	}
	public void setManuc(double manuc) {
		this.manuc = manuc;
	}
	public double getAllc() {
		return allc;
	}
	public void setAllc(double allc) {
		this.allc = allc;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getProfitrate() {
		return profitrate;
	}
	public void setProfitrate(String profitrate) {
		this.profitrate = profitrate;
	}
	
}
