/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.wl.tools.ChineseCode;

/**
 * @author Flair
 *	下载文件,给出request和response请求即可直接调用此下载方案
 */
public class DownloadUtils {
	public static void downLoadFile(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException,FileUploadException {
		String fileName = request.getParameter("filename");
		if(fileName==null){
			fileName = (String) request.getAttribute("filename");
//			fileName = ChineseCode.toUTF8(fileName);
		}else{
			fileName = ChineseCode.toUTF8(fileName);
		}
//		String fileName = ChineseCode.toUTF8(request.getParameter("filename"));
		String path = request.getRealPath("/"+fileName);  
		System.out.println(path);
		File file = new File(path);
		// 如果文件存在
		if (file.exists()) {
			// 设置响应类型及响应头
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload");
			int start = fileName.lastIndexOf("/");  			//索引到最后一个反斜杠
            fileName = fileName.substring(start+1);  			//截取 上传文件的 字符串名字，加1是 去掉反斜杠，
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
				fileName = URLEncoder.encode(fileName, "UTF-8");  
			} 
			else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");  
			}
			
			System.out.println(fileName);
			response.addHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
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
			
			
			
//			Runtime r = Runtime.getRuntime();
//			String cmd[] = {"C:/Program Files/Microsoft Office/Office15/WINWORD.EXE",path};
//			try {
//				r.exec(cmd);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}else{
//			如果文件不存在
			
		}
	}
}
