/**
 * 项目名称: work
 * 创建日期：2016-7-25
 * 修改历史：
 *		1.[2016-7-25]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class OrderTree implements Serializable{
	private static final long serialVersionUID = 1676041612391011892L;
	private String id;
	private String name;
	private String pid;
	private String cengci;
	private double ProductPrice;
	private String issueNum;
	private String drawingId;
	private String orderId;
	private int productStatus;
	private String isWaiXie;
	private String isCaiGou;
	private String isGongYi;
	private String barcode;	//xiem
	private int productNum ;
	
	
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getIsWaiXie() {
		return isWaiXie;
	}
	public void setIsWaiXie(String isWaiXie) {
		this.isWaiXie = isWaiXie;
	}
	public String getIsCaiGou() {
		return isCaiGou;
	}
	public void setIsCaiGou(String isCaiGou) {
		this.isCaiGou = isCaiGou;
	}
	public String getIsGongYi() {
		return isGongYi;
	}
	public void setIsGongYi(String isGongYi) {
		this.isGongYi = isGongYi;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCengci() {
		return cengci;
	}
	public void setCengci(String cengci) {
		this.cengci = cengci;
	}
	public double getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(double productPrice) {
		ProductPrice = productPrice;
	}
	public String getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
