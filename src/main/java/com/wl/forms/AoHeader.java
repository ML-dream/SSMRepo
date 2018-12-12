/**
 * 项目名称: work
 * 创建日期：2016-7-14
 * 修改历史：
 *		1.[2016-7-14]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class AoHeader implements Serializable {
	private static final long serialVersionUID = 6812516246606879614L;
	private String orderId;
	private String productId;
	private String issueNum;
	private String drawingId;
	private String aoContent;
	private int productNum;
	private String deadLine;
	private String aoHeadermemo;
	private String productName;
	private String productType;
	private String productTypeName;
	private String aoPaper;
	private String spec;
	private String aoId;
	
	
	public String getAoId() {
		return aoId;
	}
	public void setAoId(String aoId) {
		this.aoId = aoId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAoPaper() {
		return aoPaper;
	}
	public void setAoPaper(String aoPaper) {
		this.aoPaper = aoPaper;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
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
	public String getAoContent() {
		return aoContent;
	}
	public void setAoContent(String aoContent) {
		this.aoContent = aoContent;
	}
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getAoHeadermemo() {
		return aoHeadermemo;
	}
	public void setAoHeadermemo(String aoHeadermemo) {
		this.aoHeadermemo = aoHeadermemo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
