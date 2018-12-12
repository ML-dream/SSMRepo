/**
 * 项目名称: work
 * 创建日期：2016-6-16
 * 修改历史：
 *		1.[2016-6-16]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class OutAssist implements Serializable {

	private static final long serialVersionUID = 8861959793886202288L;
	private String orderId;
	private String deptId;
	private String deptName;
	private String companyId;
	private String companyName;
	private String principal;
	private String principalName;
	private String connectorName;
	private String connectorTel;
	private String startDate;
	private String planEndDate;
	private String trueEndDate;
	private String memo;
	private String confirmAdvice;
	private double fine;
	private double shouldPay;
	private double alreadyPay;
	private double notPay;
	private String isBusy;
	private String itemId;
	private String itemName;
	private String drawingId;
	private int num;
	private String numUnit;
	private double DetailunitPrice;
	private double DetailtotalPrice;
	private String Detaildetail;
	private String DetailstartDate;
	private String DetailplanEndDate;
	private String DetailendDate;
	private String Detailmemo;
	private int Numget;
	private String numUnitget;
	private String Dateget;
	private String Personget;
	private String personNameget;
	private String memoget;
	
	
	public String getPersonNameget() {
		return personNameget;
	}
	public void setPersonNameget(String personNameget) {
		this.personNameget = personNameget;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getConnectorName() {
		return connectorName;
	}
	public void setConnectorName(String connectorName) {
		this.connectorName = connectorName;
	}
	public String getConnectorTel() {
		return connectorTel;
	}
	public void setConnectorTel(String connectorTel) {
		this.connectorTel = connectorTel;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}
	public String getTrueEndDate() {
		return trueEndDate;
	}
	public void setTrueEndDate(String trueEndDate) {
		this.trueEndDate = trueEndDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getConfirmAdvice() {
		return confirmAdvice;
	}
	public void setConfirmAdvice(String confirmAdvice) {
		this.confirmAdvice = confirmAdvice;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public double getShouldPay() {
		return shouldPay;
	}
	public void setShouldPay(double shouldPay) {
		this.shouldPay = shouldPay;
	}
	public double getAlreadyPay() {
		return alreadyPay;
	}
	public void setAlreadyPay(double alreadyPay) {
		this.alreadyPay = alreadyPay;
	}
	public double getNotPay() {
		return notPay;
	}
	public void setNotPay(double notPay) {
		this.notPay = notPay;
	}
	public String getIsBusy() {
		return isBusy;
	}
	public void setIsBusy(String isBusy) {
		this.isBusy = isBusy;
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
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNumUnit() {
		return numUnit;
	}
	public void setNumUnit(String numUnit) {
		this.numUnit = numUnit;
	}
	public double getDetailunitPrice() {
		return DetailunitPrice;
	}
	public void setDetailunitPrice(double detailunitPrice) {
		DetailunitPrice = detailunitPrice;
	}
	public double getDetailtotalPrice() {
		return DetailtotalPrice;
	}
	public void setDetailtotalPrice(double detailtotalPrice) {
		DetailtotalPrice = detailtotalPrice;
	}
	public String getDetaildetail() {
		return Detaildetail;
	}
	public void setDetaildetail(String detaildetail) {
		Detaildetail = detaildetail;
	}
	public String getDetailstartDate() {
		return DetailstartDate;
	}
	public void setDetailstartDate(String detailstartDate) {
		DetailstartDate = detailstartDate;
	}
	public String getDetailplanEndDate() {
		return DetailplanEndDate;
	}
	public void setDetailplanEndDate(String detailplanEndDate) {
		DetailplanEndDate = detailplanEndDate;
	}
	public String getDetailendDate() {
		return DetailendDate;
	}
	public void setDetailendDate(String detailendDate) {
		DetailendDate = detailendDate;
	}
	public String getDetailmemo() {
		return Detailmemo;
	}
	public void setDetailmemo(String detailmemo) {
		Detailmemo = detailmemo;
	}
	public int getNumget() {
		return Numget;
	}
	public void setNumget(int numget) {
		Numget = numget;
	}
	public String getNumUnitget() {
		return numUnitget;
	}
	public void setNumUnitget(String numUnitget) {
		this.numUnitget = numUnitget;
	}
	public String getDateget() {
		return Dateget;
	}
	public void setDateget(String dateget) {
		Dateget = dateget;
	}
	public String getPersonget() {
		return Personget;
	}
	public void setPersonget(String personget) {
		Personget = personget;
	}
	public String getMemoget() {
		return memoget;
	}
	public void setMemoget(String memoget) {
		this.memoget = memoget;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
