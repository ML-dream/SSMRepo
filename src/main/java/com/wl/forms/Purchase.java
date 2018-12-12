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
public class Purchase implements Serializable {

	private static final long serialVersionUID = -2294260324897312740L;
	private String orderId;
	private String orderName;
	private String orderDate;
	private String orderPlace;
	private String supplierId;
	private String supplierName;
	private String startDate;
	private String endDate;
	private String buyerId;
	private String buyerName;
	private String supplyConnector;
	private String supplyTel;
	private String supplyFax;
	private int payDays;
	private String payWay;
	private String checker;
	private String supplyAddress;
	private String checkDate;
	private String memo;
	private String isCheck;
	private String isCanceled;
	private String createPerson;
	private String createTime;
	private String changePerson;
	private String changeTime;
	private double totalPrice;
	private double alreadyPay;
	private double notPay;
	private String isBusy;
	private String itemId;
	private String itemName;
	private String drawingId;
	private String spec;
	private String numUnit;
	private double num;
	private double unitPrice;
	private double sumPrice;
	private String DetailstartDate;
	private String DetailendDate;
	private String Detailmemo;
	private String DetailcreatePerson;
	private String DetailcreateTime;
	private String DetailchangePerson;
	private String DetailchangeTime;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderPlace() {
		return orderPlace;
	}
	public void setOrderPlace(String orderPlace) {
		this.orderPlace = orderPlace;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSupplyConnector() {
		return supplyConnector;
	}
	public void setSupplyConnector(String supplyConnector) {
		this.supplyConnector = supplyConnector;
	}
	public String getSupplyTel() {
		return supplyTel;
	}
	public void setSupplyTel(String supplyTel) {
		this.supplyTel = supplyTel;
	}
	public String getSupplyFax() {
		return supplyFax;
	}
	public void setSupplyFax(String supplyFax) {
		this.supplyFax = supplyFax;
	}
	public int getPayDays() {
		return payDays;
	}
	public void setPayDays(int payDays) {
		this.payDays = payDays;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getSupplyAddress() {
		return supplyAddress;
	}
	public void setSupplyAddress(String supplyAddress) {
		this.supplyAddress = supplyAddress;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public String getIsCanceled() {
		return isCanceled;
	}
	public void setIsCanceled(String isCanceled) {
		this.isCanceled = isCanceled;
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getNumUnit() {
		return numUnit;
	}
	public void setNumUnit(String numUnit) {
		this.numUnit = numUnit;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getDetailstartDate() {
		return DetailstartDate;
	}
	public void setDetailstartDate(String detailstartDate) {
		DetailstartDate = detailstartDate;
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
	public String getDetailcreatePerson() {
		return DetailcreatePerson;
	}
	public void setDetailcreatePerson(String detailcreatePerson) {
		DetailcreatePerson = detailcreatePerson;
	}
	public String getDetailcreateTime() {
		return DetailcreateTime;
	}
	public void setDetailcreateTime(String detailcreateTime) {
		DetailcreateTime = detailcreateTime;
	}
	public String getDetailchangePerson() {
		return DetailchangePerson;
	}
	public void setDetailchangePerson(String detailchangePerson) {
		DetailchangePerson = detailchangePerson;
	}
	public String getDetailchangeTime() {
		return DetailchangeTime;
	}
	public void setDetailchangeTime(String detailchangeTime) {
		DetailchangeTime = detailchangeTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
