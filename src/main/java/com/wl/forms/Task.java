package com.wl.forms;

import java.sql.Date;

import org.apache.struts.action.ActionForm;
public class Task extends ActionForm {
	
	public String Name;
	public String  UID;
	public Date Start;
	public Date Finish;/*这里应该改为Date*/
	public int PercentComplete;
	public int Duration;
	public int ParentUID;
	
	public int _id;
	public int __Index;
	public boolean isBaseline;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public Date getStart() {
		return Start;
	}
	public void setStart(Date start) {
		Start = start;
	}
	public Date getFinish() {
		return Finish;
	}
	public void setFinish(Date finish) {
		Finish = finish;
	}
	public int getPercentComplete() {
		return PercentComplete;
	}
	public void setPercentComplete(int percentComplete) {
		PercentComplete = percentComplete;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public int getParentUID() {
		return ParentUID;
	}
	public void setParentUID(int parentUID) {
		ParentUID = parentUID;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		_id = id;
	}
	public int get__Index() {
		return __Index;
	}
	public void set__Index(int index) {
		__Index = index;
	}
	public boolean isBaseline() {
		return isBaseline;
	}
	public void setBaseline(boolean isBaseline) {
		this.isBaseline = isBaseline;
	}

}
