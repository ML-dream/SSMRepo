/**
 * 
 */
package com.wl.forms;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年4月5日 下午3:53:23 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：OrdersMachinesAudits   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年4月5日 下午3:53:23   
* @version        
*/

public class OrdersMachinesAudits {

	private String orderId;
	private String machineId;
	private String auditPerson;
	private String machineName;
	private String staffName;
	private String advice;
	private String yesNo;
	
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getAuditPerson() {
		return auditPerson;
	}
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
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
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	
	
}
