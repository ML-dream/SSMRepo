/**
 * 项目名称: work
 * 创建日期：2016-7-15
 * 修改历史：
 *		1.[2016-7-15]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class AoDetail implements Serializable{
	private static final long serialVersionUID = -1270346180796252100L;
	
	private String aoId;			//ao表头编号
	private String productId;		//产品号
	private String itemId;			//物料号
	private String aoNo;			//ao编号
	private String aoVer;			//ao版本
	private String parentAoNo;		//父ao号
	private double aoTime;			//ao时间
	private String aoName;			//ao名称
	private String workplaceCode;	//工位号
	private String workplaceName;	//工位名称
	private String partNo;			//装配图号
	private String aoContent;		//装配内容
	private String memo;			//备注
	
	private String productName;		//产品号
	private String productType;
	private String issueNum;
	private String drawingId;
	private String spec;
	private int productNum;
	private String aoPaper;
	private String deadLine;
	private String isInUse="1";
	
	
	private String cut;
	private String accessory;
	private String material;
	private String measure;
	private String tool;
	
	private String cutNum;
	private String measureNum;
	private String toolNum;
	private String materialNum;
	private String accessoryNum;
	
	private String cutName;
	private String accessoryName;
	private String materialName;
	private String measureName;
	private String toolName;
	
	
	
	public String getCut() {
		return cut;
	}
	public void setCut(String cut) {
		this.cut = cut;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public String getCutNum() {
		return cutNum;
	}
	public void setCutNum(String cutNum) {
		this.cutNum = cutNum;
	}
	public String getMeasureNum() {
		return measureNum;
	}
	public void setMeasureNum(String measureNum) {
		this.measureNum = measureNum;
	}
	public String getToolNum() {
		return toolNum;
	}
	public void setToolNum(String toolNum) {
		this.toolNum = toolNum;
	}
	public String getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}
	public String getAccessoryNum() {
		return accessoryNum;
	}
	public void setAccessoryNum(String accessoryNum) {
		this.accessoryNum = accessoryNum;
	}
	public String getCutName() {
		return cutName;
	}
	public void setCutName(String cutName) {
		this.cutName = cutName;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMeasureName() {
		return measureName;
	}
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getIsInUse() {
		return isInUse;
	}
	public void setIsInUse(String isInUse) {
		this.isInUse = isInUse;
	}
	public String getAoId() {
		return aoId;
	}
	public void setAoId(String aoId) {
		this.aoId = aoId;
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
	public String getAoNo() {
		return aoNo;
	}
	public void setAoNo(String aoNo) {
		this.aoNo = aoNo;
	}
	public String getAoVer() {
		return aoVer;
	}
	public void setAoVer(String aoVer) {
		this.aoVer = aoVer;
	}
	public String getParentAoNo() {
		return parentAoNo;
	}
	public void setParentAoNo(String parentAoNo) {
		this.parentAoNo = parentAoNo;
	}
	public double getAoTime() {
		return aoTime;
	}
	public void setAoTime(double aoTime) {
		this.aoTime = aoTime;
	}
	public String getAoName() {
		return aoName;
	}
	public void setAoName(String aoName) {
		this.aoName = aoName;
	}
	public String getWorkplaceCode() {
		return workplaceCode;
	}
	public void setWorkplaceCode(String workplaceCode) {
		this.workplaceCode = workplaceCode;
	}
	public String getWorkplaceName() {
		return workplaceName;
	}
	public void setWorkplaceName(String workplaceName) {
		this.workplaceName = workplaceName;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getAoContent() {
		return aoContent;
	}
	public void setAoContent(String aoContent) {
		this.aoContent = aoContent;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getAoPaper() {
		return aoPaper;
	}
	public void setAoPaper(String aoPaper) {
		this.aoPaper = aoPaper;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}
