/**
 * 项目名称: work
 * 创建日期：2016-5-23
 * 修改历史：
 *		1.[2016-5-23]创建文件 by Flair
 */
package com.wl.forms;

import org.apache.struts.action.ActionForm;

/**
 * @author Flair
 *
 */
public class PriceManHour extends ActionForm{
	private static final long serialVersionUID = -1437428760078765380L;
	private String craftId;
	private String craftName;
	private int price;
	private String unit;
	private double addPrice;
	private double totalPrice;
	private double totalTime;
	private double grossProfit;
	
	public double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(double grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getCraftId() {
		return craftId;
	}
	public void setCraftId(String craftId) {
		this.craftId = craftId;
	}
	public String getCraftName() {
		return craftName;
	}
	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(double addPrice) {
		this.addPrice = addPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	
	
}
