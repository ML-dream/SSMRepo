/**
 * 
 */
package javaBean;

import java.util.List;

import machineOrderYuyue.beans.bookingInfoBean;

/** 
* @author 作者 :戴志强 
* @version 创建时间：2019年1月17日 上午10:46:26 
* 类说明 
*/

/*  
*    
* 项目名称：SSM   
* 类名称：machBookDatelines   
* 类描述：   
* 创建人：dell   
* 创建时间：2019年1月17日 上午10:46:26   
* @version        
*/

public class machBookDatelines {

	private String dateline;
	private String isToday;
	private String isSelected;
	private String week;
	
	private String description;
	private String machineName;
	private String machineId;
	private int isDeleted;
	private int price;
	private int sequence;
	private List<bookingInfoBean> sessionsList;
	private int state;
	private String createTime;
	
	private String deptMachineYearNo;
	
	
	
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getIsToday() {
		return isToday;
	}
	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public List<bookingInfoBean> getSessionsList() {
		return sessionsList;
	}
	public void setSessionsList(List<bookingInfoBean> sessionsList) {
		this.sessionsList = sessionsList;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDeptMachineYearNo() {
		return deptMachineYearNo;
	}
	public void setDeptMachineYearNo(String deptMachineYearNo) {
		this.deptMachineYearNo = deptMachineYearNo;
	}
	
	
	
}
