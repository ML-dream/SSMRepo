package com.wl.forms;

public class SupplierPayment {
	private String companyId;
	private String companyName;
	
	private String connector;
	private String addDate;
	private double initialPayment=0;
	
	private String payDate;
	private double thisPaid=0;
	private double qualitykk=0;
	private double tax=0;
	private String memo;
	
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
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public double getThisPaid() {
		return thisPaid;
	}
	public void setThisPaid(double thisPaid) {
		this.thisPaid = thisPaid;
	}
	public double getQualitykk() {
		return qualitykk;
	}
	public void setQualitykk(double qualitykk) {
		this.qualitykk = qualitykk;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public double getInitialPayment() {
		return initialPayment;
	}
	public void setInitialPayment(double initialPayment) {
		this.initialPayment = initialPayment;
	}
	
	
}
