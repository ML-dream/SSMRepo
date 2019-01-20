/**
 * 项目名称: work
 * 创建日期：2016-5-13
 * 修改历史：
 *		1.[2016-5-13]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


/**
 * @author Flair
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 3176152221547746510L;
	private String orderId;			//订单号
	private String orderName;			//订单号
	private String deptUser;		//使用部门
	private String lot;				//批次
	private String memo;			//附录
	private String orderType;		//订单类型
	private String orderDate;		//订单日期
	private String orderStatus;		//订单状态
	private String bookStatus;		//订单状态
	private String bookStatusName;		//订单状态name
	private String auditingStaffCode;		//订单状态name
	private String staffName;		//订单状态name
	private String connectorTelOrders;		//订单状态name
	private String checkAdvice;		//订单审核意见
	
	
	
	private String customer;		//客户
	private String createPerson;	//订单创建人
	private String createTime;		//订单创建时间
	private String changePerson;	//订单更改人
	private String changeTime;		//订单更改时间
	private String endTime;			//交付日期
	private String companyName;		//客户名称
	private String deptName;		//部门名称
	private String connector;		//联系人
	private String connectorTel;	//联系人电话
	
	/*********************以上是orders表，以下是order_detail表*********************/
	
	private String productId;		//产品号
	private String issueNum;		//版本号
	private String sortie;			//批架次,临时充当工序计划中的时间间隔
	private int productNum;		//产品数量
	private String bTime;			//开始时间
	private String eTime;			//结束时间
	private String sbTime;			//实际开始时间
	private String seTime;			//实际结束时间
	private String deptId;			//部门号
	private int productStatus;	//产品状态
	private String fproductId;		//父产品号
	private int moveNum;			//送货量
	private String upLot;			//最大批量
	private int finishNum;		//完成数量
	private String productMemo;		//产品附录
	private String planChanNo;		//计划变更号
	private String productName;		//产品名称
	private String spec;			//产品规格
	
	private String madePlace;		//制造单位	
	private String drawingId;		//图号
	private double unitPrice;		//单价
	private double planProfit;		//预估利润
	private double customerKouKuan;	//客户扣款
	private double shiJiXiaoShouer;	//实际销售额
	private double yiJingHuiKuan;	//已经回款
	private double YuKuan;			//余款
	
	private String isDuiZhangDan;	//有无对账函
	private String isLaiLiao;		//是否来料
	private String isJiaoHuo;		//是否交货
	
	
	private int willPayNum;
	private int alreadyPayNum;
	private double quotationTotal;	//报价合计
	private String confirmAdvice;	//订单审核意见
	private double grossProfit;		//毛利润
	
	private String orderPaper;		//合同文件
	private String duizhanghan;		//对账单
	private String paper;			//图纸
	private String otherPaper;		//其他文件
	private String detailOtherPaper;//其他文件
	
	private String itemTypeId;		//物料类型号
	private String itemTypeName;	//物料类型名称
	
	private String isWaiXie;
	private String isGongYi;
	private String isCaiGou;
	
	private String productType;
	private String productTypeName;
	private String confirmedAdvice;
	public String getIslailiao() {
		return islailiao;
	}
	public void setIslailiao(String islailiao) {
		this.islailiao = islailiao;
	}
	private int checkinNum;
	private int makingNum;
	///表todiscard
	private int rejectNum;  //不合格数
	private int state;     //待处理状态
	private String material;
	private String islailiao;
	private String cengci;
	private String productStatusName;		//产品状态名称
	private String barcode;
	private String foType ; 	//0 为正常件，1为报废件  xiem
	private String bday;	//页面跳转参数
	private String eday;	//页面跳转参数
	private String orderPrice;	//订单总价
	private String isExceed;	//是否超期
	private String rowIndex;	//页面跳转参数
	private String page;	//页面跳转参数
	private String status;	//页面跳转参数
	private String fcustomer;	//页面跳转参数
	private String creater;	//页面跳转参数
//	xiem 	
	private String batch="";	//批号，销售统计表
	private String productRemark="";	//备注
	private String productPrice; //产品总价
//	退货
	private String backNum ="0";
	private String backPrice = "0";
	private String backRemark = "";
	private String deliverDay = ""; //订单实际交付日期
	private String matirial="";	//原材料
	
	
	
	
	public String getCheckAdvice() {
		return checkAdvice;
	}
	public void setCheckAdvice(String checkAdvice) {
		this.checkAdvice = checkAdvice;
	}
	public String getConnectorTelOrders() {
		return connectorTelOrders;
	}
	public void setConnectorTelOrders(String connectorTelOrders) {
		this.connectorTelOrders = connectorTelOrders;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getAuditingStaffCode() {
		return auditingStaffCode;
	}
	public void setAuditingStaffCode(String auditingStaffCode) {
		this.auditingStaffCode = auditingStaffCode;
	}
	public String getBookStatusName() {
		return bookStatusName;
	}
	public void setBookStatusName(String bookStatusName) {
		this.bookStatusName = bookStatusName;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public String getMatirial() {
		return matirial;
	}
	public void setMatirial(String matirial) {
		this.matirial = matirial;
	}
	public String getDeliverDay() {
		return deliverDay;
	}
	public void setDeliverDay(String deliverDay) {
		this.deliverDay = deliverDay;
	}
	public String getBackNum() {
		return backNum;
	}
	public void setBackNum(String backNum) {
		this.backNum = backNum;
	}
	public String getBackPrice() {
		return backPrice;
	}
	public void setBackPrice(String backPrice) {
		this.backPrice = backPrice;
	}
	public String getBackRemark() {
		return backRemark;
	}
	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getProductRemark() {
		return productRemark;
	}
	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFcustomer() {
		return fcustomer;
	}
	public void setFcustomer(String fcustomer) {
		this.fcustomer = fcustomer;
	}
	public String getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getIsExceed() {
		return isExceed;
	}
	public void setIsExceed(String isExceed) {
		this.isExceed = isExceed;
	}
	public String getExceedCn() {
		return exceedCn;
	}
	public void setExceedCn(String exceedCn) {
		this.exceedCn = exceedCn;
	}
	private String exceedCn;	//是否超期
	
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	public String getEday() {
		return eday;
	}
	public void setEday(String eday) {
		this.eday = eday;
	}
	public String getFoType() {
		return foType;
	}
	public void setFoType(String foType) {
		this.foType = foType;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getProductStatusName() {
		return productStatusName;
	}
	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
	public String getCengci() {
		return cengci;
	}
	public void setCengci(String cengci) {
		this.cengci = cengci;
	}
	public void setMaterial(String material){
		this.material=material;
	}
	public String  getMaterial(){
		return material;
	}
	public int getMakingNum() {
		return makingNum;
	}
	public void setMakingNum(int makingNum) {
		this.makingNum = makingNum;
	}
	public void setRejectNum(int rejectNum){
		this.rejectNum=rejectNum;
	}
	public int getRejectNum(){
		return rejectNum;
	}
	public void setState(int state){
		this.state=state;
	}
	public int getState(){
		return state;
	}
	public String getConfirmedAdvice() {
		return confirmedAdvice;
	}
	public void setConfirmedAdvice(String confirmedAdvice) {
		this.confirmedAdvice = confirmedAdvice;
	}
	
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getIsWaiXie() {
		return isWaiXie;
	}
	public void setIsWaiXie(String isWaiXie) {
		this.isWaiXie = isWaiXie;
	}
	public String getIsGongYi() {
		return isGongYi;
	}
	public void setIsGongYi(String isGongYi) {
		this.isGongYi = isGongYi;
	}
	public String getIsCaiGou() {
		return isCaiGou;
	}
	public void setIsCaiGou(String isCaiGou) {
		this.isCaiGou = isCaiGou;
	}
	public String getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getDetailOtherPaper() {
		return detailOtherPaper;
	}
	public void setDetailOtherPaper(String detailOtherPaper) {
		this.detailOtherPaper = detailOtherPaper;
	}
	public String getOrderPaper() {
		return orderPaper;
	}
	public void setOrderPaper(String orderPaper) {
		this.orderPaper = orderPaper;
	}
	public String getDuizhanghan() {
		return duizhanghan;
	}
	public void setDuizhanghan(String duizhanghan) {
		this.duizhanghan = duizhanghan;
	}
	public String getOtherPaper() {
		return otherPaper;
	}
	public void setOtherPaper(String otherPaper) {
		this.otherPaper = otherPaper;
	}
	public double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(double grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getConfirmAdvice() {
		return confirmAdvice;
	}
	public void setConfirmAdvice(String confirmAdvice) {
		this.confirmAdvice = confirmAdvice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getWillPayNum() {
		return willPayNum;
	}
	public void setWillPayNum(int willPayNum) {
		this.willPayNum = willPayNum;
	}
	public int getAlreadyPayNum() {
		return alreadyPayNum;
	}
	public void setAlreadyPayNum(int alreadyPayNum) {
		this.alreadyPayNum = alreadyPayNum;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public String getConnectorTel() {
		return connectorTel;
	}
	public void setConnectorTel(String connectorTel) {
		this.connectorTel = connectorTel;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDeptUser() {
		return deptUser;
	}
	public void setDeptUser(String deptUser) {
		this.deptUser = deptUser;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	public String getSortie() {
		return sortie;
	}
	public void setSortie(String sortie) {
		this.sortie = sortie;
	}
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getbTime() {
		return bTime;
	}
	public void setbTime(String bTime) {
		this.bTime = bTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	public String getSbTime() {
		return sbTime;
	}
	public void setSbTime(String sbTime) {
		this.sbTime = sbTime;
	}
	public String getSeTime() {
		return seTime;
	}
	public void setSeTime(String seTime) {
		this.seTime = seTime;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getFproductId() {
		return fproductId;
	}
	public void setFproductId(String fproductId) {
		this.fproductId = fproductId;
	}
	
	public String getUpLot() {
		return upLot;
	}
	public void setUpLot(String upLot) {
		this.upLot = upLot;
	}
	
	public int getMoveNum() {
		return moveNum;
	}
	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}
	public int getFinishNum() {
		return finishNum;
	}
	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}
	public String getProductMemo() {
		return productMemo;
	}
	public void setProductMemo(String productMemo) {
		this.productMemo = productMemo;
	}
	public String getPlanChanNo() {
		return planChanNo;
	}
	public void setPlanChanNo(String planChanNo) {
		this.planChanNo = planChanNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
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
	public String getMadePlace() {
		return madePlace;
	}
	public void setMadePlace(String madePlace) {
		this.madePlace = madePlace;
	}
	public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getPlanProfit() {
		return planProfit;
	}
	public void setPlanProfit(double planProfit) {
		this.planProfit = planProfit;
	}
	public double getCustomerKouKuan() {
		return customerKouKuan;
	}
	public void setCustomerKouKuan(double customerKouKuan) {
		this.customerKouKuan = customerKouKuan;
	}
	public double getShiJiXiaoShouer() {
		return shiJiXiaoShouer;
	}
	public void setShiJiXiaoShouer(double shiJiXiaoShouer) {
		this.shiJiXiaoShouer = shiJiXiaoShouer;
	}
	public double getYiJingHuiKuan() {
		return yiJingHuiKuan;
	}
	public void setYiJingHuiKuan(double yiJingHuiKuan) {
		this.yiJingHuiKuan = yiJingHuiKuan;
	}
	public double getYuKuan() {
		return YuKuan;
	}
	public void setYuKuan(double yuKuan) {
		YuKuan = yuKuan;
	}
	
	public String getIsDuiZhangDan() {
		return isDuiZhangDan;
	}
	public void setIsDuiZhangDan(String isDuiZhangDan) {
		this.isDuiZhangDan = isDuiZhangDan;
	}
	public String getIsLaiLiao() {
		return isLaiLiao;
	}
	public void setIsLaiLiao(String isLaiLiao) {
		this.isLaiLiao = isLaiLiao;
	}
	public String getIsJiaoHuo() {
		return isJiaoHuo;
	}
	public void setIsJiaoHuo(String isJiaoHuo) {
		this.isJiaoHuo = isJiaoHuo;
	}
	public double getQuotationTotal() {
		return quotationTotal;
	}
	public void setQuotationTotal(double quotationTotal) {
		this.quotationTotal = quotationTotal;
	}
	public int getCheckinNum() {
		return checkinNum;
	}
	public void setCheckinNum(int checkinNum) {
		this.checkinNum = checkinNum;
	}
	
}
