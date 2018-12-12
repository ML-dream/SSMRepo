package com.wl.forms;

public class CheckoutDetl {
	private String checksheetId;
	private String checkoutDate;
	private String checkoutdetlId;
	private String itemId;
	private String itemName;
	private String spec;
	private String unit;
	private double checkoutNum;
	private double stockNum;
	private double unitprice;
	private double price;
	private double totalPrice=0;
	private String stockId;
	private String orderId;
	private String lot;
	private String qualityId;
	private String memo;
	private String property;
	private String issueNum;
	private String itemType;
	private String itemTypeDesc;
	private int purductNum=0;
	private double alreadyPayNum=0;
	private double noPayNum=0;
	private String createPerson;
	private String createTime;
	private String changePerson;
	private String changeTime;
	private String drawingId;
	private double  haspaid=0;
	private double nopay=0;
	private String unitm;
	
	private double totalPaid=0;
	private String companyId;
	private String companyName;
	private String connector="";
	private double arrears=0;
	private double initialPayment=0;
	private double backPrice=0;
	public String getUnitm() {
		return unitm;
	}
	public void setUnitm(String unitm) {
		this.unitm = unitm;
	}
	public String getChecksheetId() {
		return checksheetId;
	}
	public void setChecksheetId(String checksheetId) {
		this.checksheetId = checksheetId;
	}
	
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public String getCheckoutdetlId() {
		return checkoutdetlId;
	}
	public void setCheckoutdetlId(String checkoutdetlId) {
		this.checkoutdetlId = checkoutdetlId;
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
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getItemTypeDesc() {
		return itemTypeDesc;
	}
	public void setItemTypeDesc(String itemTypeDesc) {
		this.itemTypeDesc = itemTypeDesc;
	}
	public double getAlreadyPayNum() {
		return alreadyPayNum;
	}
	public void setAlreadyPayNum(double alreadyPayNum) {
		this.alreadyPayNum = alreadyPayNum;
	}
	public double getNoPayNum() {
		return noPayNum;
	}
	public void setNoPayNum(double noPayNum) {
		this.noPayNum = noPayNum;
	}
	public int getPurductNum() {
		return purductNum;
	}
	public void setPurductNum(int purductNum) {
		this.purductNum = purductNum;
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
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public double getHaspaid() {
		return haspaid;
	}
	public void setHaspaid(double haspaid) {
		this.haspaid = haspaid;
	}
	public double getNopay() {
		return nopay;
	}
	public void setNopay(double nopay) {
		this.nopay = nopay;
	}
	public double getStockNum() {
		return stockNum;
	}
	public void setStockNum(double stockNum) {
		this.stockNum = stockNum;
	}
	public double getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public double getArrears() {
		return arrears;
	}
	public void setArrears(double arrears) {
		this.arrears = arrears;
	}
	public double getInitialPayment() {
		return initialPayment;
	}
	public void setInitialPayment(double initialPayment) {
		this.initialPayment = initialPayment;
	}
	public double getBackPrice() {
		return backPrice;
	}
	public void setBackPrice(double backPrice) {
		this.backPrice = backPrice;
	}
	
	
	
}
