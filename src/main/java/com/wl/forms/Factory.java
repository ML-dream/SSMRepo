package com.wl.forms;

import org.apache.struts.action.ActionForm;

public class Factory extends ActionForm {
	private int UID;
	private String Name;
	
	private int _id;
	private int _uid;
	private int _pid;
	private int _level;
	private int _height;
	
	
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		_id = id;
	}
	public int get_uid() {
		return _uid;
	}
	public void set_uid(int uid) {
		_uid = uid;
	}
	public int get_pid() {
		return _pid;
	}
	public void set_pid(int pid) {
		_pid = pid;
	}
	public int get_level() {
		return _level;
	}
	public void set_level(int level) {
		_level = level;
	}
	public int get_height() {
		return _height;
	}
	public void set_height(int height) {
		_height = height;
	}

}
