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
public class ProcessesPlan implements Serializable {
	private static final long serialVersionUID = 5727725539464112576L;
	
	private String machineOrderStart;
	private String machineOrderEnd;
	private String processPlanId;
	private String orderId;
	private String productId;
	private String productName;
	private String issueNum;
	
	private String planId;
	private String itemId;
	private String itemName;
	private String operId;
	private String qualityId;
	
	private String barCode;
	private String levelId;
	private String fitemId;
	private String fitemName;
	private int num;
	
	private String planStartTime;
	private String planEndTime;
	private String operStatus;
	private String cancleStatus;
	private int finishNum;
	
	private String depatchPro;
	private int passNum;
	private int failureNum;
	private String isCO;
	private String deptId;
	
	private String wcId;
	private String receiveTime;
	private String isReiceive;
	private String reportStatus;
	private String jigStatus;
	
	private String rowStatus;
	private String standardStatus;
	private int shelfLife;
	private String postTime;
	private String isPost;
	
	private String aoNOUse;
	private String memo;
	private String createPerson;
	private String createTime;
	private String changePerson;
	
	private String changeTime;
	private String planPerson;
	private String planPersonName;
	private String planTime;
	
	private int duration;
	private String machineId;
	private String machineName;
	private String operName;
	private String waiXieCom;
	private String companyName;
	private String isCo;
	private String connector;
	private String connectorTel;
	private String address;
	private String workBranch;
	private String typeName;
	private String deliverTime;
	private double unitPrice;
	private double totalPrice;
	private double countPercent;
	private String actuallyEndTime;
	private String quality;
	private double qualityFine;
	private double alreadyPay;
	private double waitPay;
	private String isOpenTicket;
	private String payTime;
	private String isBusy;
	private String isMenu;
	private String menuId;
	private int totalNum;
	private String outAssistStatus;
	private String checkedAdvice;
	private String ETime;
	private String isDiscard;
	private double productPrice;
	private String fax;
	private double theoryTotalPrice;
	
	
	
	public String getMachineOrderStart() {
		return machineOrderStart;
	}
	public void setMachineOrderStart(String machineOrderStart) {
		this.machineOrderStart = machineOrderStart;
	}
	public String getMachineOrderEnd() {
		return machineOrderEnd;
	}
	public void setMachineOrderEnd(String machineOrderEnd) {
		this.machineOrderEnd = machineOrderEnd;
	}
	public double getTheoryTotalPrice() {
		return theoryTotalPrice;
	}
	public void setTheoryTotalPrice(double theoryTotalPrice) {
		this.theoryTotalPrice = theoryTotalPrice;
	}
	public void setFax(String fax)
	{
		this.fax=fax;
	}
	public String getFax(){
		return fax;
	}
	
	public void setProductPrice(double productPrice)
	{
		this.productPrice=productPrice;
	}
	public double getProductPrice(){
		return productPrice;
	}
	public void setIsDiscard(String isDiscard)
	{
		this.isDiscard=isDiscard;
	}
	public String getIsDiscard(){
		return isDiscard;
	}
	public void setETime(String ETime)
	{
		this.ETime=ETime;
	}
	public String getETime(){
		return ETime;
	}
	public void setCheckedAdvice(String checkedAdvice)
	{
		this.checkedAdvice=checkedAdvice;
	}
	public String getCheckedAdvice(){
		return checkedAdvice;
	}
	public void setOutAssistStatus(String outAssistStatus)
	{
		this.outAssistStatus=outAssistStatus;
	}
	public String getOutAssistStatus(){
		return outAssistStatus;
	}
	public void setTotalNum(int totalNum)
	{
		this.totalNum=totalNum;
	}
	public int getTotalNum(){
		return totalNum;
	}
	public void setMenuId(String menuId)
	{
		this.menuId=menuId;
	}
	public String getMenuId(){
		return menuId;
	}
	public void setIsMenu(String isMenu)
	{
		this.isMenu=isMenu;
	}
	public String getIsMenu(){
		return isMenu;
	}
	public void setIsBusy(String isBusy)
	{
		this.isBusy=isBusy;
	}
	public String getIsBusy(){
		return isBusy;
	}
	public void setPayTime(String payTime)
	{
		this.payTime=payTime;
	}
	public String getPayTime(){
		return payTime;
	}
	public void setIsOpenTicket(String isOpenTicket)
	{
		this.isOpenTicket=isOpenTicket;
	}
	public String getIsOpenTicket(){
		return isOpenTicket;
	}
	public void setWaitPay(double waitPay)
	{
		this.waitPay=waitPay;
	}
	public double getWaitPay(){
		return waitPay;
	}
	public void setAlreadyPay(double alreadyPay)
	{
		this.alreadyPay=alreadyPay;
	}
	public double getAlreadyPay(){
		return alreadyPay;
	}
	public void setQualityFine(double qualityFine)
	{
		this.qualityFine=qualityFine;
	}
	public double getQualityFine(){
		return qualityFine;
	}
	public void setQuality(String quality)
	{
		this.quality=quality;
	}
	public String getQuality(){
		return quality;
	}
	public void setActuallyEndTime(String actuallyEndTime)
	{
		this.actuallyEndTime=actuallyEndTime;
	}
	public String getActuallyEndTime(){
		return actuallyEndTime;
	}
	public double getCountPercent() {
		return countPercent;
	}
	public void setCountPercent(double countPercent) {
		this.countPercent = countPercent;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setDeliverTime(String deliverTime)
	{
		this.deliverTime=deliverTime;
	}
	public String getDeliverTime(){
		return deliverTime;
	}
	public void setTypeName(String typeName)
	{
		this.typeName=typeName;
	}
	public String getTypeName(){
		return typeName;
	}
	public void setWorkBranch(String workBranch)
	{
		this.workBranch=workBranch;
	}
	public String getWorkBranch(){
		return workBranch;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setConnectorTel(String connectorTel)
	{
		this.connectorTel=connectorTel;
	}
	public String getConnectorTel(){
		return connectorTel;
	}
	public void setConnector(String connector)
	{
		this.connector=connector;
	}
	public String getConnector(){
		return connector;
	}
	public String getIsCo(){
		return isCo;
	}
	
	public void setIsCo(String isCo){
		this.isCo=isCo;
	}
	
	public String getWaiXieCom(){
		return waiXieCom;
	}
	
	public void setWaiXieCom(String waiXieCom){ 
		this.waiXieCom=waiXieCom;
	}
	
	public String getCompanyName(){
		return companyName;
	}
	
	public void setCompanyName(String companyName){ 
		this.companyName=companyName;
	}
	public String getOperName(){
		return operName;
	}
	
	public void setOperName(String operName){ 
		this.operName=operName;
	}
	
		
	public void setMachineName(String machineName){
		this.machineName=machineName;
	}
	
	public String getMachineName(){
		return machineName;
	}
	
	
	public void setMachineId(String machineId){
		this.machineId=machineId;
	}
	public String getMachineId(){
		return machineId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getFitemName() {
		return fitemName;
	}
	public void setFitemName(String fitemName) {
		this.fitemName = fitemName;
	}
	public String getPlanPerson() {
		return planPerson;
	}
	public void setPlanPerson(String planPerson) {
		this.planPerson = planPerson;
	}
	public String getPlanPersonName() {
		return planPersonName;
	}
	public void setPlanPersonName(String planPersonName) {
		this.planPersonName = planPersonName;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getProcessPlanId() {
		return processPlanId;
	}
	public void setProcessPlanId(String processPlanId) {
		this.processPlanId = processPlanId;
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
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getFitemId() {
		return fitemId;
	}
	public void setFitemId(String fitemId) {
		this.fitemId = fitemId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getOperStatus() {
		return operStatus;
	}
	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}
	public String getCancleStatus() {
		return cancleStatus;
	}
	public void setCancleStatus(String cancleStatus) {
		this.cancleStatus = cancleStatus;
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
	public String getIsCO() {
		return isCO;
	}
	public void setIsCO(String isCO) {
		this.isCO = isCO;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getWcId() {
		return wcId;
	}
	public void setWcId(String wcId) {
		this.wcId = wcId;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getIsReiceive() {
		return isReiceive;
	}
	public void setIsReiceive(String isReiceive) {
		this.isReiceive = isReiceive;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getJigStatus() {
		return jigStatus;
	}
	public void setJigStatus(String jigStatus) {
		this.jigStatus = jigStatus;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	public String getStandardStatus() {
		return standardStatus;
	}
	public void setStandardStatus(String standardStatus) {
		this.standardStatus = standardStatus;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getIsPost() {
		return isPost;
	}
	public void setIsPost(String isPost) {
		this.isPost = isPost;
	}
	public String getAoNOUse() {
		return aoNOUse;
	}
	public void setAoNOUse(String aoNOUse) {
		this.aoNOUse = aoNOUse;
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
