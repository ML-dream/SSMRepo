/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.testaction.fileManage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class SmartUpladServlet extends HttpServlet {

	private static final long serialVersionUID = -2747125748838449477L;
	private static final String ALLOWTYPE = "zip,rar,txt,jpg,bmp,gif,doc,docx,xls,xlsx,pdf,cad,cam," +
			"cap,dot,dwg,gif,gl,jif,jpe,jpeg,jpg,mp2,mp3,mpe,mpeg,mpg,pdf,ppt,tz,wav,mp4,xlk,xll,xlm,xls,xlt,xlv,xlw,flac";
	private static final String DENIEDTYPE = "jsp,js,html,css,exe";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTf-8");
		// 文件上传个数
		int count = 0;
		// 文件上传地址
//		String filePath = getServletContext().getRealPath("/") + "smartUpload";
		String filePath = "E:/test";

		// 如果文件夹不存在 则创建这个文件夹
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		// 初始化对象
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), request, response);

		// 设置文件最大容量
		su.setMaxFileSize(100 * 1024 * 1024);
		// 设置所有文件最大容量
		su.setTotalMaxFileSize(500 * 1024 * 1024);
		// 设置上传文件类型
		su.setAllowedFilesList(ALLOWTYPE);

		try {
			// 设置禁止上传的文件类型
			su.setDeniedFilesList(DENIEDTYPE);
			// 上传文件
			su.upload();
			System.out.println("userName="+ su.getRequest().getParameter("userName"));
			count = su.save(filePath);
		} catch (SQLException e) {			//这里的异常要进行处理！！！！！！！！
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < su.getFiles().getCount(); i++) {
			com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
			System.out.println("-------------------------------------------------");
			System.out.println("表单项名称:" + tempFile.getFieldName());
			System.out.println("文件名：" + tempFile.getFileName());
			System.out.println("文件长度：" + tempFile.getSize());
			System.out.println("文件扩展名:" + tempFile.getFileExt());
			System.out.println("文件全名：" + tempFile.getFilePathName());
			System.out.println("-------------------------------------------------");
		}
		System.out.println("上传成功！共" + count + "个文件！");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
