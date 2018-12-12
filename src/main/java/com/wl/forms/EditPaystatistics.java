package com.wl.forms;

public class EditPaystatistics {
	private String companyId;
	private String companyName;
	private String maintenanceDate;
	private double editEndPayment=0;
	private double initialEndPayment=0;
	private String editPerson;
	private String editPersonName;
	private String editTime;
	private String reason="";
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
	public String getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public double getEditEndPayment() {
		return editEndPayment;
	}
	public void setEditEndPayment(double editEndPayment) {
		this.editEndPayment = editEndPayment;
	}
	public double getInitialEndPayment() {
		return initialEndPayment;
	}
	public void setInitialEndPayment(double initialEndPayment) {
		this.initialEndPayment = initialEndPayment;
	}
	public String getEditPerson() {
		return editPerson;
	}
	public void setEditPerson(String editPerson) {
		this.editPerson = editPerson;
	}
	public String getEditPersonName() {
		return editPersonName;
	}
	public void setEditPersonName(String editPersonName) {
		this.editPersonName = editPersonName;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
