package machineOrderYuyue.beans;

public class dateContent {

	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Boolean getIsToday() {
		return isToday;
	}
	public void setIsToday(Boolean isToday) {
		this.isToday = isToday;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	private Boolean isSelected;
	private Boolean isToday;
	private int week;
}
