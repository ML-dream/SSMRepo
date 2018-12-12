/**
 * 项目名称: work
 * 创建日期：2016-6-7
 * 修改历史：
 *		1.[2016-6-7]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;


/**
 * @author Flair
 *
 */
@SuppressWarnings("unchecked")
public class FoDetail  implements Serializable,Comparable{

	private static final long serialVersionUID = 2833801796461068481L;
	
	private String machineTime01;
	private String machineTime0101;
	private String machineName;
	private String foId;
	private String orderId;
	private String productType;
	private String productId;
	private String issueNum;
	private String itemId;
	private String foNo;
	private String foVer;
	private String foOperId;
	private String foOpName;
	private String foOpcontent;
	private double ratedTime;
	private double planTime;
	private double operAidTime;
	private double inspTime;
	private double operTime;
	private String isKey;
	private String isInsp;
	private String isArmInsp;
	private String isCerTop;
	private String isCo;
	private String WCID;
	private String equipType;
	private String equipCode;
	private String department;
	private String section;
	private String isFirst;
	private String isAssembe;
	private String demension;
	private String imgUrl;
	private int productNum;
	
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
	
	private String workBranchId;
	private String workBranchName;
	
	private String createPerson;
	private String createTime;
	private String status;
	
	private String confirmAdvice;
	
	private String craftPaper;
	private String isInUse;
	private String drawingId;
	private String isWaiXie;
	
	
	
	public String getMachineTime01() {
		return machineTime01;
	}
	public void setMachineTime01(String machineTime01) {
		this.machineTime01 = machineTime01;
	}
	public String getMachineTime0101() {
		return machineTime0101;
	}
	public void setMachineTime0101(String machineTime0101) {
		this.machineTime0101 = machineTime0101;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getIsWaiXie(){
		return isWaiXie;
	}
	public void setIsWaiXie(String isWaiXie){
		this.isWaiXie=isWaiXie;
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
	public String getFoId() {
		return foId;
	}
	public void setFoId(String foId) {
		this.foId = foId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getIsInUse() {
		return isInUse;
	}
	public void setIsInUse(String isInUse) {
		this.isInUse = isInUse;
	}
	public String getCraftPaper() {
		return craftPaper;
	}
	public void setCraftPaper(String craftPaper) {
		this.craftPaper = craftPaper;
	}
	public String getConfirmAdvice() {
		return confirmAdvice;
	}
	public void setConfirmAdvice(String confirmAdvice) {
		this.confirmAdvice = confirmAdvice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
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
	public String getFoNo() {
		return foNo;
	}
	public void setFoNo(String foNo) {
		this.foNo = foNo;
	}
	public String getFoVer() {
		return foVer;
	}
	public void setFoVer(String foVer) {
		this.foVer = foVer;
	}
	public String getFoOperId() {
		return foOperId;
	}
	public void setFoOperId(String foOperId) {
		this.foOperId = foOperId;
	}
	public String getFoOpName() {
		return foOpName;
	}
	public void setFoOpName(String foOpName) {
		this.foOpName = foOpName;
	}
	public String getFoOpcontent() {
		return foOpcontent;
	}
	public void setFoOpcontent(String foOpcontent) {
		this.foOpcontent = foOpcontent;
	}
	public double getRatedTime() {
		return ratedTime;
	}
	public void setRatedTime(double ratedTime) {
		this.ratedTime = ratedTime;
	}
	public double getPlanTime() {
		return planTime;
	}
	public void setPlanTime(double planTime) {
		this.planTime = planTime;
	}
	public double getOperAidTime() {
		return operAidTime;
	}
	public void setOperAidTime(double operAidTime) {
		this.operAidTime = operAidTime;
	}
	public double getInspTime() {
		return inspTime;
	}
	public void setInspTime(double inspTime) {
		this.inspTime = inspTime;
	}
	public double getOperTime() {
		return operTime;
	}
	public void setOperTime(double operTime) {
		this.operTime = operTime;
	}
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	public String getIsInsp() {
		return isInsp;
	}
	public void setIsInsp(String isInsp) {
		this.isInsp = isInsp;
	}
	public String getIsArmInsp() {
		return isArmInsp;
	}
	public void setIsArmInsp(String isArmInsp) {
		this.isArmInsp = isArmInsp;
	}
	public String getIsCerTop() {
		return isCerTop;
	}
	public void setIsCerTop(String isCerTop) {
		this.isCerTop = isCerTop;
	}
	public String getIsCo() {
		return isCo;
	}
	public void setIsCo(String isCo) {
		this.isCo = isCo;
	}
	public String getWCID() {
		return WCID;
	}
	public void setWCID(String wCID) {
		WCID = wCID;
	}
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getEquipCode() {
		return equipCode;
	}
	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
	public String getIsAssembe() {
		return isAssembe;
	}
	public void setIsAssembe(String isAssembe) {
		this.isAssembe = isAssembe;
	}
	public String getDemension() {
		return demension;
	}
	public void setDemension(String demension) {
		this.demension = demension;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getWorkBranchId() {
		return workBranchId;
	}
	public void setWorkBranchId(String workBranchId) {
		this.workBranchId = workBranchId;
	}
	public String getWorkBranchName() {
		return workBranchName;
	}
	public void setWorkBranchName(String workBranchName) {
		this.workBranchName = workBranchName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		FoDetail foDetail = (FoDetail)obj;
		int foNo = Integer.parseInt(foDetail.getFoNo());
		return Integer.parseInt(this.foNo)-foNo>0?0:1;
	}
	
	
	
	


}
