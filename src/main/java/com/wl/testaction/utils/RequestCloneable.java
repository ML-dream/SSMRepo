/**
 * 项目名称: work
 * 创建日期：2016-7-13
 * 修改历史：
 *		1.[2016-7-13]创建文件 by Flair
 */
package com.wl.testaction.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Flair
 *
 */
public class RequestCloneable extends HttpServletRequestWrapper implements Cloneable{
	private HttpServletRequest request;
	public RequestCloneable(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public HttpServletRequest clone() throws CloneNotSupportedException {
		HttpServletRequest req = (HttpServletRequest)super.clone();
		return req;
	}

}
