package machineOrderYuyue.beans;

import java.util.List;

public class machineBean {
	
	private String description;
	private String machineName;
	private int machineId;
	private int isDeleted;
	private int price;
	private int sequence;
	private List<bookingInfoBean> sessionsList;
	private int state;
	private String createTime;
	
	private String deptMachineYearNo;
	
	
	public String getDeptMachineYearNo() {
		return deptMachineYearNo;
	}
	public void setDeptMachineYearNo(String deptMachineYearNo) {
		this.deptMachineYearNo = deptMachineYearNo;
	}
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
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
	
	
	
}
