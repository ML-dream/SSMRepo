package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.payStatistics;
import com.wl.tools.Sqlhelper;

public class getAddDataMaintenanceFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getAddDataMaintenanceFormServlet() {
		super();
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

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String companyId=request.getParameter("companyId");
//		String maintenanceDate=request.getParameter("maintenanceDate");
		String maintenanceDate="";
		
		String minSql="select min(maintenancedate) from paystatistics where companyid='"+companyId+"'";
		
		try {
			maintenanceDate=Sqlhelper.exeQueryString(minSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		String Sql="select A.*,companyName from paystatistics A " +
				"left join customer B on B.companyid=A.companyid " +
				"where A.companyid='"+companyId+"' and maintenancedate=to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss')";
		payStatistics payStatisticsBean=new payStatistics();
		
		try {
			payStatisticsBean=Sqlhelper.exeQueryBean(Sql, null, payStatistics.class);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		String json=PluSoft.Utils.JSON.Encode(payStatisticsBean);
		System.out.println(json);
		response.getWriter().append(json).flush();
		
	}

}
