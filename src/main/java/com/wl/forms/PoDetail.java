package com.wl.forms;

public class PoDetail {
	private String poSheetid="";
	private String itemId="";
	private String itemName="";
	private String spec="";
	private String unit="";
	private String kind="";
	private String itemType="";
	private String usage="";
//	private int poNum=0;
	private double poNum;
	public double getPoNum() {
		return poNum;
	}
	public void setPoNum(double poNum) {
		this.poNum = poNum;
	}
	private double unitPrice=0;
	private double price=0;
	private String memo="";
	private String orderId="";
	private String changeperson="";
	private String changetime="";
	private String createperson="";
	private String createtime="";
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
//	public int getPoNum() {
//		return poNum;
//	}
//	public void setPoNum(int poNum) {
//		this.poNum = poNum;
//	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPoSheetid() {
		return poSheetid;
	}
	public void setPoSheetid(String poSheetid) {
		this.poSheetid = poSheetid;
	}
	public String getChangeperson() {
		return changeperson;
	}
	public void setChangeperson(String changeperson) {
		this.changeperson = changeperson;
	}
	public String getChangetime() {
		return changetime;
	}
	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}
	public String getCreateperson() {
		return createperson;
	}
	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
