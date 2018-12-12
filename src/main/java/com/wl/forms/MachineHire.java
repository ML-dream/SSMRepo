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
 */
public class MachineHire implements Serializable{

	private static final long serialVersionUID = -2161225763325944093L;
	private String machineId;
	private String deptId;
	private String deptName;
	private String hireStatus;
	private String outDate;
	private String inDate;
	private String backDate;
	private double hireMoney;
	private int hireNum;
	private String memo;
	private String principal;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public String getHireStatus() {
		return hireStatus;
	}
	public void setHireStatus(String hireStatus) {
		this.hireStatus = hireStatus;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public double getHireMoney() {
		return hireMoney;
	}
	public void setHireMoney(double hireMoney) {
		this.hireMoney = hireMoney;
	}
	public int getHireNum() {
		return hireNum;
	}
	public void setHireNum(int hireNum) {
		this.hireNum = hireNum;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
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
