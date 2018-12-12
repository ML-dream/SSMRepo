package com.wl.forms;

public class OrderItem {
	private String productId;
	private String productName;
	private String issueNum;
	private String productType;
	private String productTypeDesc;
	private String drawingId;
	private String spec;
	private String unitm;
	private String stockId;
	private double unitPrice=0;
	private int purductNum=0;
	private int alreadyPayNum=0;
	private int noPayNum=0;
	private double checkoutNum=0;
	private double price=0;
	private double stockNum=0;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	
	
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public String getUnitm() {
		return unitm;
	}
	public void setUnitm(String unitm) {
		this.unitm = unitm;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getProductTypeDesc() {
		return productTypeDesc;
	}
	public void setProductTypeDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getPurductNum() {
		return purductNum;
	}
	public void setPurductNum(int purductNum) {
		this.purductNum = purductNum;
	}
	
	
	public int getAlreadyPayNum() {
		return alreadyPayNum;
	}
	public void setAlreadyPayNum(int alreadyPayNum) {
		this.alreadyPayNum = alreadyPayNum;
	}
	public int getNoPayNum() {
		return noPayNum;
	}
	public void setNoPayNum(int noPayNum) {
		this.noPayNum = noPayNum;
	}
	public double getCheckoutNum() {
		return checkoutNum;
	}
	public void setCheckoutNum(double checkoutNum) {
		this.checkoutNum = checkoutNum;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getStockNum() {
		return stockNum;
	}
	public void setStockNum(double stockNum) {
		this.stockNum = stockNum;
	}
	
}
