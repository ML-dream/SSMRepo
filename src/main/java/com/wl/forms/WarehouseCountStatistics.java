package com.wl.forms;

public class WarehouseCountStatistics {
	private String sheetId="";
	private String countDate="";
	private String warehouseId="";
	private String warehouseName="";
	private String itemId="";
	private String itemName="";
	private String spec="";
	private double beginCountPrice=0;
	private double beginCountNum=0;
	private String unit="";
	private double checkinNum=0;
	private double checkoutNum=0;
	private double endCountNum=0;
	public String getSheetId() {
		return sheetId;
	}
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public double getBeginCountPrice() {
		return beginCountPrice;
	}
	public void setBeginCountPrice(double beginCountPrice) {
		this.beginCountPrice = beginCountPrice;
	}
	public double getBeginCountNum() {
		return beginCountNum;
	}
	public void setBeginCountNum(double beginCountNum) {
		this.beginCountNum = beginCountNum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getCheckinNum() {
		return checkinNum;
	}
	public void setCheckinNum(double checkinNum) {
		this.checkinNum = checkinNum;
	}
	public double getCheckoutNum() {
		return checkoutNum;
	}
	public void setCheckoutNum(double checkoutNum) {
		this.checkoutNum = checkoutNum;
	}
	public double getEndCountNum() {
		return endCountNum;
	}
	public void setEndCountNum(double endCountNum) {
		this.endCountNum = endCountNum;
	}
	
}
