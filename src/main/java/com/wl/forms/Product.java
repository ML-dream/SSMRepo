/**
 * 项目名称: work
 * 创建日期：2016-8-4
 * 修改历史：
 *		1.[2016-8-4]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class Product implements Serializable{
	private static final long serialVersionUID = 6748451072918253290L;
	
	private String orderId;			//订单号
	private String productId;		//产品号
	private String issueNum;		//版本号
	private String drawingId;		//图号
	private String productName;		//产品名称
	private String spec;			//产品规格
	private int productNum;			//产品数量
	private String bTime;			//开始时间
	private String eTime;			//结束时间
	private String sbTime;			//实际开始时间
	private String seTime;			//实际结束时间
	private int productStatus;		//产品状态
	private String productStatusName;
	private int finishNum;			//完成数量
	private String orderDate;		//订单接收日期
	private String productMemo;		//产品附录
	private double unitPrice;		//单价
	private double planProfit;		//预估利润
	private double customerKouKuan;	//客户扣款
	private double shiJiXiaoShouer;	//实际销售额
	private double yiJingHuiKuan;	//已经回款
	private double YuKuan;			//余款
	private String isDuiZhangDan;	//有无对账函
	private String isLaiLiao;		//是否来料
	private String isJiaoHuo;		//是否交货
	private int willPayNum;			//计划交付数量
	private int alreadyPayNum;		//已经交付数量
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
	private String isWaiXie;		//是否外协
	private String isGongYi;		//是否做工艺
	private String isCaiGou;		//是否采购
	private String productType;		//产品类型
	private String productTypeName;	//产品类型名称
	private int partPlanId;			//零件计划号
	private String qualityId;		//质检号
	private String fatherItemId;	//父无聊号
	private String partPlanPerpon;	//零件计划人
	private String partPlantime;	//零件计划时间
	private int partPlanNum;		//零件计划数量
	private String Partbegintime;	//计划开始时间
	private String Partendtime;		//计划结束时间
	private int Partstatus;		//零件计划状态
	private String barCode;			//条码号
	private String cancleStatus;	//是否取消
	private String depatchPro;		//
	private int passNum;			//合格数量
	private int failureNum;			//失败数量
	private float finishRation;		//完成率
	private String isReceive;		//是否接收
	private String receiveDate;		//接收时间
	private float receiveRate;		//接收率
	private String toolStatus;		//
	private String cutStatus;
	private String measureStatus;
	private String mtlStatus;
	private String accessoryStatus;
	private int checkinNum;			//入库数量
	private int checkoutNum;		//出库数量
	private int moveQuity;			//
	private String checkinDate;		//入库时间
	private String checkoutDate;	//出库时间
	private String checkStatus;		//质检状态
	private int planDuration;	//计划工序时间
	private String foOpcontent;		//工序内容
	private int totalDuration;	//总工时
	private String endTime;      //订单结束时间

	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProductStatusName() {
		return productStatusName;
	}
	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
	public int getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
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
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
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
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
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
	public double getQuotationTotal() {
		return quotationTotal;
	}
	public void setQuotationTotal(double quotationTotal) {
		this.quotationTotal = quotationTotal;
	}
	public String getConfirmAdvice() {
		return confirmAdvice;
	}
	public void setConfirmAdvice(String confirmAdvice) {
		this.confirmAdvice = confirmAdvice;
	}
	public double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(double grossProfit) {
		this.grossProfit = grossProfit;
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
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getOtherPaper() {
		return otherPaper;
	}
	public void setOtherPaper(String otherPaper) {
		this.otherPaper = otherPaper;
	}
	public String getDetailOtherPaper() {
		return detailOtherPaper;
	}
	public void setDetailOtherPaper(String detailOtherPaper) {
		this.detailOtherPaper = detailOtherPaper;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public int getPartPlanId() {
		return partPlanId;
	}
	public void setPartPlanId(int partPlanId) {
		this.partPlanId = partPlanId;
	}
	public String getQualityId() {
		return qualityId;
	}
	public void setQualityId(String qualityId) {
		this.qualityId = qualityId;
	}
	public String getFatherItemId() {
		return fatherItemId;
	}
	public void setFatherItemId(String fatherItemId) {
		this.fatherItemId = fatherItemId;
	}
	public String getPartPlanPerpon() {
		return partPlanPerpon;
	}
	public void setPartPlanPerpon(String partPlanPerpon) {
		this.partPlanPerpon = partPlanPerpon;
	}
	public String getPartPlantime() {
		return partPlantime;
	}
	public void setPartPlantime(String partPlantime) {
		this.partPlantime = partPlantime;
	}
	public int getPartPlanNum() {
		return partPlanNum;
	}
	public void setPartPlanNum(int partPlanNum) {
		this.partPlanNum = partPlanNum;
	}
	public String getPartbegintime() {
		return Partbegintime;
	}
	public void setPartbegintime(String partbegintime) {
		Partbegintime = partbegintime;
	}
	public String getPartendtime() {
		return Partendtime;
	}
	public void setPartendtime(String partendtime) {
		Partendtime = partendtime;
	}
	
	public int getPartstatus() {
		return Partstatus;
	}
	public void setPartstatus(int partstatus) {
		Partstatus = partstatus;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getCancleStatus() {
		return cancleStatus;
	}
	public void setCancleStatus(String cancleStatus) {
		this.cancleStatus = cancleStatus;
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
	public float getFinishRation() {
		return finishRation;
	}
	public void setFinishRation(float finishRation) {
		this.finishRation = finishRation;
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
	public float getReceiveRate() {
		return receiveRate;
	}
	public void setReceiveRate(float receiveRate) {
		this.receiveRate = receiveRate;
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
	public String getMtlStatus() {
		return mtlStatus;
	}
	public void setMtlStatus(String mtlStatus) {
		this.mtlStatus = mtlStatus;
	}
	public String getAccessoryStatus() {
		return accessoryStatus;
	}
	public void setAccessoryStatus(String accessoryStatus) {
		this.accessoryStatus = accessoryStatus;
	}
	public int getCheckinNum() {
		return checkinNum;
	}
	public void setCheckinNum(int checkinNum) {
		this.checkinNum = checkinNum;
	}
	public int getCheckoutNum() {
		return checkoutNum;
	}
	public void setCheckoutNum(int checkoutNum) {
		this.checkoutNum = checkoutNum;
	}
	public int getMoveQuity() {
		return moveQuity;
	}
	public void setMoveQuity(int moveQuity) {
		this.moveQuity = moveQuity;
	}
	public String getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	public int getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(int planDuration) {
		this.planDuration = planDuration;
	}
	public String getFoOpcontent() {
		return foOpcontent;
	}
	public void setFoOpcontent(String foOpcontent) {
		this.foOpcontent = foOpcontent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	

}
