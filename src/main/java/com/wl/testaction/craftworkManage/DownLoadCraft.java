package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.wl.testaction.utils.DownloadUtils;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class DownLoadCraft extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DownLoadCraft() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issue");
		String foNo = request.getParameter("foNo");
		
		String sqla = "select t.craftpaper from FO_DETAIL t where t.product_id =? and t.issue_num = ? and t.fo_no =? and t.isinuse='1' ";
		String [] params = {productId,issueNum,foNo};
		String filename = "";
		try {
			filename += Sqlhelper.exeQueryString(sqla, params);
			request.setAttribute("filename", filename);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			DownloadUtils.downLoadFile(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().append("").flush();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
