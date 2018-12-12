package com.wl.forms;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class Partplan extends ActionForm {
	private static final long serialVersionUID = -328460666554244882L;
	private String orderID;
	private String productID;
	private String productName;
	private String issuenum;
	private String drawingId;
	private String productNum;
	private String productStatus;
	private String productTypeId;
	private String productTypeName;
	private String spec;
	private int planid;
	private String itemid;
	private String qualityid;
	private String fatheritemid;
	private String planpeo;
	private String plantime;
	private int partnum;
	private Date partbegintime;
	private Date partendtime;
	private String partstatus;
	private String codeid;
	private String canclestatus;
	private int finishnum;
	private String depatchpro;
	private int passnum;
	private int failurenum;
	private float finishration;
	private String isreceive;
	private Date receivedate;
	private float receiverate;
	private String toolstatus;
	private String cutstatus;
	private String measurestatus;
	private String mtlstatus;
	private String accessorystatus;
	private float shelflife;
	private int checkinnum;
	private int checkoutnum;
	private int movequity;
	private Date checkindate;
	private String posttime;
	private int ischange;
	private String checkstatus;
	private String wlreceive;
	private String partplana;
	private String partplanb;
	private String memo;
	private String partname;
	private Date plan_time;
	private int peoplenum;
    private String picturenum;
    private double planDuration;
    
    
	public double getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(double planDuration) {
		this.planDuration = planDuration;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
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
	public String getPosttime() {
		return posttime;
	}
	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
	public int getIschange() {
		return ischange;
	}
	public void setIschange(int ischange) {
		this.ischange = ischange;
	}
	public String getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getIssuenum() {
		return issuenum;
	}
	public void setIssuenum(String issuenum) {
		this.issuenum = issuenum;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getQualityid() {
		return qualityid;
	}
	public void setQualityid(String qualityid) {
		this.qualityid = qualityid;
	}
	public String getFatheritemid() {
		return fatheritemid;
	}
	public void setFatheritemid(String fatheritemid) {
		this.fatheritemid = fatheritemid;
	}
	public String getPlanpeo() {
		return planpeo;
	}
	public void setPlanpeo(String planpeo) {
		this.planpeo = planpeo;
	}
	public String getPlantime() {
		return plantime;
	}
	public void setPlantime(String plantime) {
		this.plantime = plantime;
	}
	public int getPartnum() {
		return partnum;
	}
	public void setPartnum(int partnum) {
		this.partnum = partnum;
	}
	public Date getPartbegintime() {
		return partbegintime;
	}
	public void setPartbegintime(Date partbegintime) {
		this.partbegintime = partbegintime;
	}
	public Date getPartendtime() {
		return partendtime;
	}
	public void setPartendtime(Date partendtime) {
		this.partendtime = partendtime;
	}
	public String getPartstatus() {
		return partstatus;
	}
	public void setPartstatus(String partstatus) {
		this.partstatus = partstatus;
	}
	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	public String getCanclestatus() {
		return canclestatus;
	}
	public void setCanclestatus(String canclestatus) {
		this.canclestatus = canclestatus;
	}
	public int getFinishnum() {
		return finishnum;
	}
	public void setFinishnum(int finishnum) {
		this.finishnum = finishnum;
	}
	public String getDepatchpro() {
		return depatchpro;
	}
	public void setDepatchpro(String depatchpro) {
		this.depatchpro = depatchpro;
	}
	public int getPassnum() {
		return passnum;
	}
	public void setPassnum(int passnum) {
		this.passnum = passnum;
	}
	public int getFailurenum() {
		return failurenum;
	}
	public void setFailurenum(int failurenum) {
		this.failurenum = failurenum;
	}
	public float getFinishration() {
		return finishration;
	}
	public void setFinishration(float finishration) {
		this.finishration = finishration;
	}
	public String getIsreceive() {
		return isreceive;
	}
	public void setIsreceive(String isreceive) {
		this.isreceive = isreceive;
	}
	public Date getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}
	public float getReceiverate() {
		return receiverate;
	}
	public void setReceiverate(float receiverate) {
		this.receiverate = receiverate;
	}
	public String getToolstatus() {
		return toolstatus;
	}
	public void setToolstatus(String toolstatus) {
		this.toolstatus = toolstatus;
	}
	public String getCutstatus() {
		return cutstatus;
	}
	public void setCutstatus(String cutstatus) {
		this.cutstatus = cutstatus;
	}
	public String getMeasurestatus() {
		return measurestatus;
	}
	public void setMeasurestatus(String measurestatus) {
		this.measurestatus = measurestatus;
	}
	public String getMtlstatus() {
		return mtlstatus;
	}
	public void setMtlstatus(String mtlstatus) {
		this.mtlstatus = mtlstatus;
	}
	public String getAccessorystatus() {
		return accessorystatus;
	}
	public void setAccessorystatus(String accessorystatus) {
		this.accessorystatus = accessorystatus;
	}
	public float getShelflife() {
		return shelflife;
	}
	public void setShelflife(float shelflife) {
		this.shelflife = shelflife;
	}
	public int getCheckinnum() {
		return checkinnum;
	}
	public void setCheckinnum(int checkinnum) {
		this.checkinnum = checkinnum;
	}
	public int getCheckoutnum() {
		return checkoutnum;
	}
	public void setCheckoutnum(int checkoutnum) {
		this.checkoutnum = checkoutnum;
	}
	public int getMovequity() {
		return movequity;
	}
	public void setMovequity(int movequity) {
		this.movequity = movequity;
	}
	public Date getCheckindate() {
		return checkindate;
	}
	public void setCheckindate(Date checkindate) {
		this.checkindate = checkindate;
	}
	public String getWlreceive() {
		return wlreceive;
	}
	public void setWlreceive(String wlreceive) {
		this.wlreceive = wlreceive;
	}
	public String getPartplana() {
		return partplana;
	}
	public void setPartplana(String partplana) {
		this.partplana = partplana;
	}
	public String getPartplanb() {
		return partplanb;
	}
	public void setPartplanb(String partplanb) {
		this.partplanb = partplanb;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPartname() {
		return partname;
	}
	public void setPartname(String partname) {
		this.partname = partname;
	}
	public Date getPlan_time() {
		return plan_time;
	}
	public void setPlan_time(Date planTime) {
		plan_time = planTime;
	}
	public int getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(int peoplenum) {
		this.peoplenum = peoplenum;
	}
	public String getPicturenum() {
		return picturenum;
	}
	public void setPicturenum(String picturenum) {
		this.picturenum = picturenum;
	}
    

}
