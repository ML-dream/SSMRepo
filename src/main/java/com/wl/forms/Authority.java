/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;

/**
 * @author Flair
 *
 */
public class Authority implements Serializable {

	private static final long serialVersionUID = 3536959334907180605L;
	private String authorityId;
	private String authorityName;
	
	
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
