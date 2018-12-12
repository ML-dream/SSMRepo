package com.xm.testaction.qualitycheck.sum;

public class ProductSumDetailBean {
	private String fono;
	private String foopname;
	private int acceptnum;
	private int rejectnum;
	private int fixnum;
	private int partnum;	//计划数量
	private double usedtime;	//花费工时
	private String state;	//工序状态
	private String remark;
	private String isco; 
	private String companyname;
	private String stateName;
	private String qualityLoss;		//质量损失
	
	public String getQualityLoss() {
		return qualityLoss;
	}
	public void setQualityLoss(String qualityLoss) {
		this.qualityLoss = qualityLoss;
	}
	public String getIsco() {
		return isco;
	}
	public void setIsco(String isco) {
		this.isco = isco;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFono() {
		return fono;
	}
	public void setFono(String fono) {
		this.fono = fono;
	}
	public String getFoopname() {
		return foopname;
	}
	public void setFoopname(String foopname) {
		this.foopname = foopname;
	}
	public int getAcceptnum() {
		return acceptnum;
	}
	public void setAcceptnum(int acceptnum) {
		this.acceptnum = acceptnum;
	}
	public int getRejectnum() {
		return rejectnum;
	}
	public void setRejectnum(int rejectnum) {
		this.rejectnum = rejectnum;
	}
	public int getFixnum() {
		return fixnum;
	}
	public void setFixnum(int fixnum) {
		this.fixnum = fixnum;
	}
	public int getPartnum() {
		return partnum;
	}
	public void setPartnum(int partnum) {
		this.partnum = partnum;
	}
	public double getUsedtime() {
		return usedtime;
	}
	public void setUsedtime(double usedtime) {
		this.usedtime = usedtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
