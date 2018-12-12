package com.xm.testaction.qualitycheck.sum;

public class BeanWorkerDetail {
	private String  checkDate;
	private String  machineId;
	private String  orderId;
	private String  productId;
	private String  productName;

	private String  barcode;
	private int  	inputNum;
	private int 	confirmNum;
	private String  foOpname;
	private String  workBranch;

	private double  price;
	private double  ratedTime;
	private String baseTime;
	
	private int foNo;
	private String staffCode;
	private String staffName;
	private String qualityLoss;		//质量损失
	private int accept;		//合格数
	private int reject;		//不合格数
	public int getAccept() {
		return accept;
	}
	public void setAccept(int accept) {
		this.accept = accept;
	}
	public int getReject() {
		return reject;
	}
	public void setReject(int reject) {
		this.reject = reject;
	}
	public int getFoNo() {
		return foNo;
	}
	public void setFoNo(int foNo) {
		this.foNo = foNo;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getQualityLoss() {
		return qualityLoss;
	}
	public void setQualityLoss(String qualityLoss) {
		this.qualityLoss = qualityLoss;
	}
	public String getBaseTime() {
		return baseTime;
	}
	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}
	private int	acceptNum;
	private int  	disNum;
	private int  	fixNum;
	
	private double  rewardsTime;
	private String  finalTime;
	public String getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}
	private String  checker;
	private String timeValue;	//工时金额
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public int getInputNum() {
		return inputNum;
	}
	public void setInputNum(int inputNum) {
		this.inputNum = inputNum;
	}
	public int getConfirmNum() {
		return confirmNum;
	}
	public void setConfirmNum(int confirmNum) {
		this.confirmNum = confirmNum;
	}
	public String getFoOpname() {
		return foOpname;
	}
	public void setFoOpname(String foOpname) {
		this.foOpname = foOpname;
	}
	public String getWorkBranch() {
		return workBranch;
	}
	public void setWorkBranch(String workBranch) {
		this.workBranch = workBranch;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRatedTime() {
		return ratedTime;
	}
	public void setRatedTime(double ratedTime) {
		this.ratedTime = ratedTime;
	}
	public int getAcceptNum() {
		return acceptNum;
	}
	public void setAcceptNum(int acceptNum) {
		this.acceptNum = acceptNum;
	}
	public int getDisNum() {
		return disNum;
	}
	public void setDisNum(int disNum) {
		this.disNum = disNum;
	}
	public int getFixNum() {
		return fixNum;
	}
	public void setFixNum(int fixNum) {
		this.fixNum = fixNum;
	}
	public double getRewardsTime() {
		return rewardsTime;
	}
	public void setRewardsTime(double rewardsTime) {
		this.rewardsTime = rewardsTime;
	}
	
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
}
