package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class SumInMachine extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SumInMachine() {
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
		"select a.orderid,a.usedtime " +
		"from summachtem a " +
			"where  a.btime = to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') and a.etime = to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') )" ;
		
		ResultSet rsb = null;
		int count = 0 ;		//	判断是否有记录
		try {
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			count = rsb.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rsb!=null){
					rsb.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(count == 0){
			
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
