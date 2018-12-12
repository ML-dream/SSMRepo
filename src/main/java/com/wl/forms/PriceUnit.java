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
public class PriceUnit extends ActionForm{

	private static final long serialVersionUID = -2999589823927924382L;
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
