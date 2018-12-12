/**
 * 项目名称: work
 * 创建日期：2016-6-7
 * 修改历史：
 *		1.[2016-6-7]创建文件 by Flair
 */
package com.wl.forms;

import java.io.Serializable;


/**
 * @author Flair
 *
 */
public class OrderHead implements Serializable{

	private static final long serialVersionUID = -2363267407535051095L;
	private String orderHead;
	private String orderHeadMean;
	
	public String getOrderHead() {
		return orderHead;
	}
	public void setOrderHead(String orderHead) {
		this.orderHead = orderHead;
	}
	public String getOrderHeadMean() {
		return orderHeadMean;
	}
	public void setOrderHeadMean(String orderHeadMean) {
		this.orderHeadMean = orderHeadMean;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
