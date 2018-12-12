package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringUtil;

public class SumInTime extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SumInTime() {
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

		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH);	//+1  是当前月 
		int date = c.get(Calendar.DATE); 
		
		String bdate= "" +year +"-"+month+"-"+date;
		String edate= "" +year +"-"+(month+1)+"-"+date;
		
		
		bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bdate:request.getParameter("bdate");
		edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?edate:request.getParameter("edate");
		
		String sqlb ="select count(*) from (" +
		"select rownum rn,a.staff_code " +
		"from empsumtem a " +
			"where  a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') )" ;

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
