/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.testaction.fileManage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Flair
 *
 */

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = -2370487263714044331L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {   
		new FileUploadUtils().upload(request, response,"wl","fileManage/filedemo.jsp");
	}

}
