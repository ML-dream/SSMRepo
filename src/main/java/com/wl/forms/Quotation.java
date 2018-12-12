/**
 * 项目名称: work
 * 创建日期：2016-6-2
 * 修改历史：
 *		1.[2016-6-2]创建文件 by Flair
 */
package com.wl.forms;

import org.apache.struts.action.ActionForm;
/**
 * @author Flair
 *
 */
public class Quotation extends ActionForm{
	private static final long serialVersionUID = 7208043243505562347L;
	private String orderId ;
	private String productId ;
	private String itemId ;
	private String craftId ;
	private double price ;
	private String priceUnit ;
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getCraftId() {
		return craftId;
	}
	public void setCraftId(String craftId) {
		this.craftId = craftId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
