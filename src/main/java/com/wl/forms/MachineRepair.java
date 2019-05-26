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
public class MachineRepair implements Serializable{

	private static final long serialVersionUID = -6688176650119485889L;
	private String machineId;
	private String machine;
	private String repairPart;
	private String startDate;
	private String endDate;
	private String errorDate;
	private String repairDate;
	private String repairFactory;
	private double repairPrice;
	private String repairDetail;
	private String principal;
	private String memo;
	private String machineName;
	private String staffName;
	
	
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getRepairPart() {
		return repairPart;
	}
	public void setRepairPart(String repairPart) {
		this.repairPart = repairPart;
	}
	public String getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(String errorDate) {
		this.errorDate = errorDate;
	}
	public String getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
	public String getRepairFactory() {
		return repairFactory;
	}
	public void setRepairFactory(String repairFactory) {
		this.repairFactory = repairFactory;
	}
	public double getRepairPrice() {
		return repairPrice;
	}
	public void setRepairPrice(double repairPrice) {
		this.repairPrice = repairPrice;
	}
	public String getRepairDetail() {
		return repairDetail;
	}
	public void setRepairDetail(String repairDetail) {
		this.repairDetail = repairDetail;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
