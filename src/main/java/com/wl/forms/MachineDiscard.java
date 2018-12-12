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
public class MachineDiscard implements Serializable{

	private static final long serialVersionUID = -6688176650119485889L;
	private String machineId;
	private String deptId;
	private String deptName;
	private String discardDate;
	private String discardTo;
	private double discardMoney;
	private String principal;
	private String memo;
	private String createPerson;
	private String createTime;
	private String changePerson;
	private String changeTime;
	private String staffName;
	
	
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDiscardDate() {
		return discardDate;
	}
	public void setDiscardDate(String discardDate) {
		this.discardDate = discardDate;
	}
	public String getDiscardTo() {
		return discardTo;
	}
	public void setDiscardTo(String discardTo) {
		this.discardTo = discardTo;
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
	public double getDiscardMoney() {
		return discardMoney;
	}
	public void setDiscardMoney(double discardMoney) {
		this.discardMoney = discardMoney;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}
