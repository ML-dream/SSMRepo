package com.wl.forms;

import java.sql.Date;


import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionForm;


public class Parts extends ActionForm {
//	static{   
//        ConvertUtils.register(new DateConverter(), Date.class);   
//    }  
	private String part_id;	
	private String part_pid;
	private Date starttime;
	private Date endtime;
	private Date preparetime;
	private Date plantime;
	private int peoplenm;
	private String picnm;
	
	public String getPart_id() {
		return part_id;
	}
	public void setPart_id(String partId) {
		part_id = partId;
	}
	public String getPart_pid() {
		return part_pid;
	}
	public void setPart_pid(String partPid) {
		part_pid = partPid;
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
	public String getPicnm() {
		return picnm;
	}
	public void setPicnm(String picnm) {
		this.picnm = picnm;
	}
}
