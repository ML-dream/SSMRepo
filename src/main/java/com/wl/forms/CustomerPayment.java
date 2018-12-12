package com.wl.forms;

public class CustomerPayment {
	private String customerId;
	private String companyName;
	private String connector;
	private String addDate;
	private double initialPayment=0;
	private String payDate;
	private double thisPaid;
	private String hasPaid;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public String getHasPaid() {
		return hasPaid;
	}
	public void setHasPaid(String hasPaid) {
		this.hasPaid = hasPaid;
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
