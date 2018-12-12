package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.cardhandle.RejStateRunnum;

public class ToFixTable extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToFixTable() {
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

		String runNum = "";		// 废品单单号
		runNum = RejStateRunnum.fixRunnum();
		
//		9-6  往 fixdetail表中插入数据
		String staterunnum=request.getParameter("staterunnum");	//审理单单号
		String  dissql="insert into fixdetail (runnum,staterunnum) values ('"+runNum+"','"+staterunnum+"')";
		Sqlhelper0.executeUpdate(dissql, null);
		System.out.println(dissql +"ok");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(runNum).flush();
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
