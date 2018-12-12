/**
 * 项目名称: work
 * 创建日期：2016-6-2
 * 修改历史：
 *		1.[2016-6-2]创建文件 by Flair
 */
package com.wl.forms;

import java.util.Date;

import org.apache.struts.action.ActionForm;
/**
 * @author Flair
 *
 */
public class OrderPayed extends ActionForm{
	
	private static final long serialVersionUID = -6411629456890570827L;
	private String orderId ;
	private String productId ;
	private String FProductId ;
	private int payedNum ;
	private String payedTime ;
	private String payedPerson ;
	private String productName;
	
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
	public String getFProductId() {
		return FProductId;
	}
	public void setFProductId(String fProductId) {
		FProductId = fProductId;
	}
	public String getPayedPerson() {
		return payedPerson;
	}
	public void setPayedPerson(String payedPerson) {
		this.payedPerson = payedPerson;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPayedTime() {
		return payedTime;
	}
	public void setPayedTime(String payedTime) {
		this.payedTime = payedTime;
	}
	public int getPayedNum() {
		return payedNum;
	}
	public void setPayedNum(int payedNum) {
		this.payedNum = payedNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
