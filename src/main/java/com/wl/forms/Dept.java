/**
 * 项目名称: work
 * 创建日期：2016-6-7
 * 修改历史：
 *		1.[2016-6-7]创建文件 by Flair
 */
package com.wl.forms;

import org.apache.struts.action.ActionForm;

/**
 * @author Flair
 *
 */
public class Dept extends ActionForm{

	private static final long serialVersionUID = 5971374063080565320L;
	private String deptId;
	private String headStaffId;
	private String deptKing;
	private String FDeptId;
	private int deptLevel;
	private String deptName;
	private String isKey;
	private String memo;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getHeadStaffId() {
		return headStaffId;
	}
	public void setHeadStaffId(String headStaffId) {
		this.headStaffId = headStaffId;
	}
	public String getDeptKing() {
		return deptKing;
	}
	public void setDeptKing(String deptKing) {
		this.deptKing = deptKing;
	}
	public String getFDeptId() {
		return FDeptId;
	}
	public void setFDeptId(String fDeptId) {
		FDeptId = fDeptId;
	}
	public int getDeptLevel() {
		return deptLevel;
	}
	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
