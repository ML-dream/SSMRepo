package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class EndDisTable extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EndDisTable() {
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
		String runnum = "";
//		从废品表、审理单表 取数据    这个是报废单  保存 
		runnum = request.getParameter("runnum");
		
		String sqla= "update disdetail a set a.ishandle =1 where a.runnum= '"+runnum+"'";
		try {
			System.out.println(sqla);
			Sqlhelper0.executeUpdate(sqla, null);
			System.out.println("ok "+sqla);
			response.setCharacterEncoding("utf-8");
			response.getWriter().append("").flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
