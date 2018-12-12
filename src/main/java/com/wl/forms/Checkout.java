package com.wl.forms;

public class Checkout {
	private String checkoutDate;
	private String checksheetId;
	private String orderId="";
	private String warehouseId;
	private String warehouseName;
	private String checkoutKind;
	private String companyid;
	private String connector;
	private String connectortel;
	private String delivery;
	private String operator;
	private String companyname;
	private String deliveryName;
	private String operatorName;
	private String createPerson;
	private String createTime;
	private String changePerson;
	private String changeTime;
	private String status;
	private String opinion;
	private String examinePerson;
	private String checkoutType;
	private String checkoutTypeDesc;
	private String orderStatus;
	private String orderStatusDesc;
	private String itemId;
	private String itemName;
	private String spec="";
	private String unit="";
	private double checkoutNum;
	private double unitPrice;
	private double price;
	private double tax;
	private double totalPrice;
	private int isreceipted;
	private String isreceiptedCN;
	public String getIsreceiptedCN() {
		return isreceiptedCN;
	}
	public void setIsreceiptedCN(int isreceiptedCN) {
		String s="否";
		if(isreceipted==1){
			s="是";
		}
		this.isreceiptedCN = s;
	}
	public int getIsreceipted() {
		return isreceipted;
	}
	public void setIsreceipted(int isreceipted) {
		this.isreceipted = isreceipted;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public String getChecksheetId() {
		return checksheetId;
	}
	public void setChecksheetId(String checksheetId) {
		this.checksheetId = checksheetId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getCheckoutKind() {
		return checkoutKind;
	}
	public void setCheckoutKind(String checkoutKind) {
		this.checkoutKind = checkoutKind;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public String getConnectortel() {
		return connectortel;
	}
	public void setConnectortel(String connectortel) {
		this.connectortel = connectortel;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getChangePerson() {
		return changePerson;
	}
	public void setChangePerson(String changePerson) {
		this.changePerson = changePerson;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getExaminePerson() {
		return examinePerson;
	}
	public void setExaminePerson(String examinePerson) {
		this.examinePerson = examinePerson;
	}
	public String getCheckoutType() {
		return checkoutType;
	}
	public void setCheckoutType(String checkoutType) {
		this.checkoutType = checkoutType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCheckoutTypeDesc() {
		return checkoutTypeDesc;
	}
	public void setCheckoutTypeDesc(String checkoutTypeDesc) {
		this.checkoutTypeDesc = checkoutTypeDesc;
	}
	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

	
}
