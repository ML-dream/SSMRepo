/**
 * 项目名称: work
 * 创建日期：2016-8-4
 * 修改历史：
 *		1.[2016-8-4]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class ProductStatus implements Serializable{
	private static final long serialVersionUID = -3056084747365148546L;
	private int statusId;
	private String statusName;
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
