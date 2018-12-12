/**
 * 项目名称: work
 * 创建日期：2016-5-30
 * 修改历史：
 *		1.[2016-5-30]创建文件 by Flair
 */
package com.wl.forms;

import org.apache.struts.action.ActionForm;

/**
 * @author Flair
 *
 */
public class MiniTree extends ActionForm{
	private static final long serialVersionUID = 9200882548158363287L;
	
	private String id;
	private String text;
	private String pid;
	private String url;
	private String icon;
	
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
