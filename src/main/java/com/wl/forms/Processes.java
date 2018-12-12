package com.wl.forms;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class Processes extends ActionForm {
	
	private String process_id;
	private String process_name;
	private Date starttime;
	private Date endtime;
	private Date preparetime;
	private Date plantime;
	private int peoplenm;
	private String jobtype;
	private String picnm;
	private String process_pid;
	
	
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String processId) {
		process_id = processId;
	}
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String processName) {
		process_name = processName;
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
	public Date getPreparetime() {
		return preparetime;
	}
	public void setPreparetime(Date preparetime) {
		this.preparetime = preparetime;
	}
	public Date getPlantime() {
		return plantime;
	}
	public void setPlantime(Date plantime) {
		this.plantime = plantime;
	}
	public int getPeoplenm() {
		return peoplenm;
	}
	public void setPeoplenm(int peoplenm) {
		this.peoplenm = peoplenm;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public String getPicnm() {
		return picnm;
	}
	public void setPicnm(String picnm) {
		this.picnm = picnm;
	}
	public String getProcess_pid() {
		return process_pid;
	}
	public void setProcess_pid(String processPid) {
		process_pid = processPid;
	}


}
