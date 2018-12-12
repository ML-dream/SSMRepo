package com.wl.forms;

public class PrDetail {
	private String prDate;
	private String prSheetid;
	private String seq;
	private String itemId;
	private String itemName;
	private String itemType;
	private String itemTypeName;
	
	private String spec="";
	private String unit="";
	private String ussage;
	
	private double prNum;
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	private double unitPrice;
	private double price;
	
	private double taxrate;
	private double tax;
	private String stockId;
	private String memo;
	
	private String drawNum;
	private String issueNum;
	private String orderId;
	
	private String createPerson;
	private String createTime;
	private String changePerson;
	private String changeTime;
	private String poSheetid;
	
	private double inNum;
	private String warehouseId="g01";
	private String warehousename;
	
	private String remark;
	private String status;
	private String itemState;//采购入库审核，物料状态
	private String statusEd;	//原审核结果，用来处理由通过修改为不通过的库存问题
	private int payMethod=1;
	
	private String customerId;
	private String customerName;
	private String connector;
	private String paymethodText;
	
	public String getPaymethodText() {
		return paymethodText;
	}

	public void setPaymethodText(String paymethodText) {
		this.paymethodText = paymethodText;
	}

	public String getStatusEd() {
		return statusEd;
	}
	
	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	public void setStatusEd(String statusEd) {
		this.statusEd = statusEd;
	}
	public String getItemState() {
		return itemState;
	}
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public double getInNum() {
		return inNum;
	}
	public void setInNum(double inNum) {
		this.inNum = inNum;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getPrSheetid() {
		return prSheetid;
	}
	public void setPrSheetid(String prSheetid) {
		this.prSheetid = prSheetid;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
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
	
	public String getUssage() {
		return ussage;
	}
	public void setUssage(String ussage) {
		this.ussage = ussage;
	}
	public double getPrNum() {
		return prNum;
	}
	public void setPrNum(double prNum) {
		this.prNum = prNum;
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
	
	public double getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(double taxrate) {
		this.taxrate = taxrate;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getDrawNum() {
		return drawNum;
	}
	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}
	public String getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getPoSheetid() {
		return poSheetid;
	}
	public void setPoSheetid(String poSheetid) {
		this.poSheetid = poSheetid;
	}

	public String getPrDate() {
		return prDate;
	}

	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}

	
	
}
