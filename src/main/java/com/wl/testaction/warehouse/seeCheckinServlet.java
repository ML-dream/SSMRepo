package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkin;
import com.wl.tools.Sqlhelper;

public class seeCheckinServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheet_id");
		
		String sql="select checkin_date,checksheet_id,company_id,checkin_kind,examine,admin,delivery,B.warehouse_id,EM.warehouse_name," +
		"B.deptid, C.companyname companyName,D.deptname deptName, E.staff_name examineName,F.staff_Name adminName, T.staff_name deliveryName " +
		"from mycheckin B left join CUSTOMER C on C.companyid=B.company_id " +
		"left join dept D on D.deptid=B.deptid " +
		"left join EMPLOYEE_INFO E on E.staff_code=B.examine " +
		"left join EMPLOYEE_INFO F on F.staff_code=B.admin " +
		"left join EMPLOYEE_INFO T on T.staff_code=B.delivery " +
		"left join warehouse EM on EM.warehouse_id=B.warehouse_id" +
		" where checksheet_id='"+checksheet_id+"'";
		
		Checkin checkin=new Checkin();
		try{
			checkin=Sqlhelper.exeQueryBean(sql, null, Checkin.class);
    		request.setAttribute("checkin", checkin);
  				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Checkin/seeDetail.jsp").forward(request, response);
		
		
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

		this.doGet(request, response);
	}

}
