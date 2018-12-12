/**
 * 项目名称: work
 * 创建日期：2016-5-13
 * 修改历史：
 *		1.[2016-5-13]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;


/**
 * @author Flair
 *
 */
public class WorkBranch implements Serializable{
	
	private static final long serialVersionUID = -1112737124032285657L;
	private String id;
	private String text;
	private int sortedNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getSortedNum() {
		return sortedNum;
	}
	public void setSortedNum(int sortedNum) {
		this.sortedNum = sortedNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
