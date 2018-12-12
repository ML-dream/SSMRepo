/**
 * 项目名称: work
 * 创建日期：2016-6-16
 * 修改历史：
 *		1.[2016-6-16]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class CompanyType implements Serializable {

	private static final long serialVersionUID = 786524956291748853L;
	private String id;
	private String text;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
