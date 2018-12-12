package com.wl.forms;

import java.sql.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionForm;

public class ProcessPlan extends ActionForm {
    
    private   String orderID;
    private   String productId;
    private   String issueid;
    private   int    planid;
    private   String itemid;
    private   String processid;
    private   int    processplanid;
    private   String qualityid;
    private   String barcode;
    private   String levelid;
    private   String fatheritemid;
    private   int    num;
    private   Date   starttime;
    private   Date   endtime;
    private   String processstate;
    private   String idcancle;
    private   int    finishnum;
    private   String depatchpro;
    private   int    passnum;
    private   int    failurenum;
    private   String isco;
    private   String deptid;
    private   String workcore;
    private   Date   receivetime;
    private   String isreceive;
    private   String reportstatus;
    private   String jigstatus;
    private   String rowstatus;
    private   String standardstatus;
    private   int    shelflife;
    private   String processplana;
    private   String aonouse;
    private   String processplanb;
    private   Date   guaqitime;
    private   String isguaqi;
    
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getIssueid() {
		return issueid;
	}
	public void setIssueid(String issueid) {
		this.issueid = issueid;
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
	public String getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid = processid;
	}
	public int getProcessplanid() {
		return processplanid;
	}
	public void setProcessplanid(int processplanid) {
		this.processplanid = processplanid;
	}
	public String getQualityid() {
		return qualityid;
	}
	public void setQualityid(String qualityid) {
		this.qualityid = qualityid;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getLevelid() {
		return levelid;
	}
	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}
	public String getFatheritemid() {
		return fatheritemid;
	}
	public void setFatheritemid(String fatheritemid) {
		this.fatheritemid = fatheritemid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getProcessstate() {
		return processstate;
	}
	public void setProcessstate(String processstate) {
		this.processstate = processstate;
	}
	public String getIdcancle() {
		return idcancle;
	}
	public void setIdcancle(String idcancle) {
		this.idcancle = idcancle;
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
	public String getIsco() {
		return isco;
	}
	public void setIsco(String isco) {
		this.isco = isco;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getWorkcore() {
		return workcore;
	}
	public void setWorkcore(String workcore) {
		this.workcore = workcore;
	}
	public Date getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}
	public String getIsreceive() {
		return isreceive;
	}
	public void setIsreceive(String isreceive) {
		this.isreceive = isreceive;
	}
	public String getReportstatus() {
		return reportstatus;
	}
	public void setReportstatus(String reportstatus) {
		this.reportstatus = reportstatus;
	}
	public String getJigstatus() {
		return jigstatus;
	}
	public void setJigstatus(String jigstatus) {
		this.jigstatus = jigstatus;
	}
	public String getRowstatus() {
		return rowstatus;
	}
	public void setRowstatus(String rowstatus) {
		this.rowstatus = rowstatus;
	}
	public String getStandardstatus() {
		return standardstatus;
	}
	public void setStandardstatus(String standardstatus) {
		this.standardstatus = standardstatus;
	}
	public int getShelflife() {
		return shelflife;
	}
	public void setShelflife(int shelflife) {
		this.shelflife = shelflife;
	}
	public String getProcessplana() {
		return processplana;
	}
	public void setProcessplana(String processplana) {
		this.processplana = processplana;
	}
	public String getAonouse() {
		return aonouse;
	}
	public void setAonouse(String aonouse) {
		this.aonouse = aonouse;
	}
	public String getProcessplanb() {
		return processplanb;
	}
	public void setProcessplanb(String processplanb) {
		this.processplanb = processplanb;
	}
	public Date getGuaqitime() {
		return guaqitime;
	}
	public void setGuaqitime(Date guaqitime) {
		this.guaqitime = guaqitime;
	}
	public String getIsguaqi() {
		return isguaqi;
	}
	public void setIsguaqi(String isguaqi) {
		this.isguaqi = isguaqi;
	}
    
    
}