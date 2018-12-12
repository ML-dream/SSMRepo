/**
 * 项目名称: work
 * 创建日期：2016-7-18
 * 修改历史：
 *		1.[2016-7-18]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class PartsPlan implements Serializable{
	private static final long serialVersionUID = -2200709374444913016L;
	
	private String partsPlanId;
	private String orderId;
	private String productId;
	private String issueNum;
	private String itemId;
	private String qualityId;
	private String fitemId;
	private String planPerson;
	private String planTime;
	private int partNum;
	private String planBTime;
	private String planETime;
	private int partStatus;
	private String codeId;
	private String cancelStatus;
	private int finishNum;
	private String depatchPro;
	private int passNum;
	private int failureNum;
	private double finishRate;
	private String isReceive;
	private String receiveDate;
	private double reduceRate;
	private String toolStatus;
	private String cutStatus;
	private String measureStatus;
	private String materialStatus;
	private String accessoryStatus;
	private int shelfLife;
	private int checkInNum;
	private int checkOutNum;
	private int moveQuity;
	private String checkInDate;
	private String postTime;
	private String isChange;
	private String checkStatus;
	private String wlReceive;
	private String memo;
	private String createPerson;
	private String createTime;
	private String changePerson;
	private String changeTime;
	private String productName;
	private String drawingId;
	private int productNum;
	private String productStatus;
	private String productTypeId;
	private String productTypeName;
	private String spec;
	private String fproductId;
	private String barcode;
	

	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getFproductId() {
		return fproductId;
	}
	public void setFproductId(String fproductId) {
		this.fproductId = fproductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPartsPlanId() {
		return partsPlanId;
	}
	public void setPartsPlanId(String partsPlanId) {
		this.partsPlanId = partsPlanId;
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getFitemId() {
		return fitemId;
	}
	public void setFitemId(String fitemId) {
		this.fitemId = fitemId;
	}
	public String getPlanPerson() {
		return planPerson;
	}
	public void setPlanPerson(String planPerson) {
		this.planPerson = planPerson;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public int getPartNum() {
		return partNum;
	}
	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}
	public String getPlanBTime() {
		return planBTime;
	}
	public void setPlanBTime(String planBTime) {
		this.planBTime = planBTime;
	}
	public String getPlanETime() {
		return planETime;
	}
	public void setPlanETime(String planETime) {
		this.planETime = planETime;
	}
	
	public int getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(int partStatus) {
		this.partStatus = partStatus;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public int getFinishNum() {
		return finishNum;
	}
	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}
	public String getDepatchPro() {
		return depatchPro;
	}
	public void setDepatchPro(String depatchPro) {
		this.depatchPro = depatchPro;
	}
	public int getPassNum() {
		return passNum;
	}
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}
	public int getFailureNum() {
		return failureNum;
	}
	public void setFailureNum(int failureNum) {
		this.failureNum = failureNum;
	}
	public double getFinishRate() {
		return finishRate;
	}
	public void setFinishRate(double finishRate) {
		this.finishRate = finishRate;
	}
	public String getIsReceive() {
		return isReceive;
	}
	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public double getReduceRate() {
		return reduceRate;
	}
	public void setReduceRate(double reduceRate) {
		this.reduceRate = reduceRate;
	}
	public String getToolStatus() {
		return toolStatus;
	}
	public void setToolStatus(String toolStatus) {
		this.toolStatus = toolStatus;
	}
	public String getCutStatus() {
		return cutStatus;
	}
	public void setCutStatus(String cutStatus) {
		this.cutStatus = cutStatus;
	}
	public String getMeasureStatus() {
		return measureStatus;
	}
	public void setMeasureStatus(String measureStatus) {
		this.measureStatus = measureStatus;
	}
	public String getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}
	public String getAccessoryStatus() {
		return accessoryStatus;
	}
	public void setAccessoryStatus(String accessoryStatus) {
		this.accessoryStatus = accessoryStatus;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	public int getCheckInNum() {
		return checkInNum;
	}
	public void setCheckInNum(int checkInNum) {
		this.checkInNum = checkInNum;
	}
	public int getCheckOutNum() {
		return checkOutNum;
	}
	public void setCheckOutNum(int checkOutNum) {
		this.checkOutNum = checkOutNum;
	}
	public int getMoveQuity() {
		return moveQuity;
	}
	public void setMoveQuity(int moveQuity) {
		this.moveQuity = moveQuity;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getIsChange() {
		return isChange;
	}
	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getWlReceive() {
		return wlReceive;
	}
	public void setWlReceive(String wlReceive) {
		this.wlReceive = wlReceive;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
