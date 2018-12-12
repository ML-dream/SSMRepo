/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.testaction.fileManage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowFileServlet extends HttpServlet {

	private static final long serialVersionUID = 4431220306924313898L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "E:/test";
		// 声明集合存放目录下所有文件的文件名
		List<String> fileList = new ArrayList<String>();
		File folder = new File(path);
		// 判断文件夹是否存在并且是否是一个目录
		if (folder.exists() && folder.isDirectory()) {
			// 获得目录中所有文件及目录
			File[] files = folder.listFiles();
			for (File file : files) {
				// 如果是文件
				if (file.isFile()) {
					// 将文件名放入集合中
					fileList.add(file.getName());
				}
			}
		}
		request.setAttribute("fileList", fileList);
		request.getRequestDispatcher("fileManage/showfile.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}