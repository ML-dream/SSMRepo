/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.testaction.fileManage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadUtils {
	public void downLoadFile(HttpServletRequest request, HttpServletResponse response,String filePathName)
			throws ServletException, IOException {
//		String path = "E:/feiqiu";
//		String fileName = request.getParameter("filename");
		File file = new File(filePathName);
		// 如果文件存在
		if (file.exists()) {
			// 设置响应类型及响应头
			response.setContentType("application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment; filename=\""+ filePathName + "\"");
			// 读取文件
			InputStream inputStream = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			byte[] bytes = new byte[1024];
			ServletOutputStream outStream = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(outStream);
			int readLength = 0;
			while ((readLength = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, readLength);
			}
			// 释放资源
			inputStream.close();
			bis.close();
			bos.flush();
			outStream.close();
			bos.close();
		}
	}
}
