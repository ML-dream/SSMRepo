package com.wl.forms;

import org.apache.struts.action.ActionForm;

public class Tree extends ActionForm {
	private String tree_id;
	private String tree_pid;
	private String tree_name;
	private String cengci;
	public String getTree_id() {
		return tree_id;
	}
	public void setTree_id(String treeId) {
		tree_id = treeId;
	}
	public String getTree_pid() {
		return tree_pid;
	}
	public void setTree_pid(String treePid) {
		tree_pid = treePid;
	}
	public String getTree_name() {
		return tree_name;
	}
	public void setTree_name(String treeName) {
		tree_name = treeName;
	}
	public String getCengci() {
		return cengci;
	}
	public void setCengci(String cengci) {
		this.cengci = cengci;
	}

}
