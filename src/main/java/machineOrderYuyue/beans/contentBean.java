package machineOrderYuyue.beans;

import java.util.ArrayList;
import java.util.List;

import javaBean.machBookDatelines;

public class contentBean {
	
	private  List machines = null;
	private  List<timeLinesBean> timeLines = null;
	private  int deptId ;
	private String deptName = "";
	private String selectedDate;
	private List<machBookDatelines> machBookDatelines;
	
	
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	public List getMachines() {
		return machines;
	}
	public void setMachines(List machines) {
		this.machines = machines;
	}
	public List<timeLinesBean> getTimeLines() {
		return timeLines;
	}
	public void setTimeLines(List<timeLinesBean> timeLinesList) {
		this.timeLines = timeLinesList;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<machBookDatelines> getMachBookDatelines() {
		return machBookDatelines;
	}
	public void setMachBookDatelines(List<machBookDatelines> machBookDatelines) {
		this.machBookDatelines = machBookDatelines;
	}
	
	
	

}
