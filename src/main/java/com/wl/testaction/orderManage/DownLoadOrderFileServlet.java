/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.testaction.orderManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.wl.testaction.utils.DownloadUtils;

public class DownLoadOrderFileServlet extends HttpServlet {
	private static final long serialVersionUID = -6581266783685582668L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DownloadUtils.downLoadFile(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
