package com.xm.testaction.qualitycheck.statejudge;

public class WaitJudgeBean {
	private String runnum;
	private int fo_no;
	private String fo_opname;
	private String checkdate;
	private String barcode;
	private String remark;
	private String customer;
	private String rejNum;
	private String dutyerName="";
	private String dutyer;
	private String reason;
	private String handle;
	private String qualityLoss;
	private String proSource;
	private String qualityFee="";	//质量罚款
	private String shouldBe="";	//应测值
	private String realBe="";	//实测值
	private String companyId ="";	//客户
	private String companyName="";	
	private String productId;
	private String productName;
	private String outCompany;	//外协商
	
	private String rejType;	//不合格原因类型
	private String opinion;	//处理意见
	private String rejTypeName;
	
	private String numSum;	//不合格数量总计
	private String lossSum;	//质量损失总计
	private String feeSum;	//质量罚款总计
	public String getNumSum() {
		return numSum;
	}
	public void setNumSum(String numSum) {
		this.numSum = numSum;
	}
	public String getLossSum() {
		return lossSum;
	}
	public void setLossSum(String lossSum) {
		this.lossSum = lossSum;
	}
	public String getFeeSum() {
		return feeSum;
	}
	public void setFeeSum(String feeSum) {
		this.feeSum = feeSum;
	}
	public String getRejTypeName() {
		return rejTypeName;
	}
	public void setRejTypeName(String rejTypeName) {
		this.rejTypeName = rejTypeName;
	}
	public String getOutCompany() {
		return outCompany;
	}
	public void setOutCompany(String outCompany) {
		this.outCompany = outCompany;
	}
	public String getRejType() {
		return rejType;
	}
	public void setRejType(String rejType) {
		this.rejType = rejType;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	public String getShouldBe() {
		return shouldBe;
	}
	public void setShouldBe(String shouldBe) {
		this.shouldBe = shouldBe;
	}
	public String getRealBe() {
		return realBe;
	}
	public void setRealBe(String realBe) {
		this.realBe = realBe;
	}
	public String getQualityFee() {
		return qualityFee;
	}
	public void setQualityFee(String qualityFee) {
		this.qualityFee = qualityFee;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getRejNum() {
		return rejNum;
	}
	public void setRejNum(String rejNum) {
		this.rejNum = rejNum;
	}
	public String getDutyerName() {
		return dutyerName;
	}
	public void setDutyerName(String dutyerName) {
		this.dutyerName = dutyerName;
	}
	public String getDutyer() {
		return dutyer;
	}
	public void setDutyer(String dutyer) {
		this.dutyer = dutyer;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getQualityLoss() {
		return qualityLoss;
	}
	public void setQualityLoss(String qualityLoss) {
		this.qualityLoss = qualityLoss;
	}
	public String getProSource() {
		return proSource;
	}
	public void setProSource(String proSource) {
		this.proSource = proSource;
	}
	public String getRunnum() {
		return runnum;
	}
	public void setRunnum(String runnum) {
		this.runnum = runnum;
	}
	public int getFo_no() {
		return fo_no;
	}
	public void setFo_no(int foNo) {
		fo_no = foNo;
	}
	public String getFo_opname() {
		return fo_opname;
	}
	public void setFo_opname(String foOpname) {
		fo_opname = foOpname;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
