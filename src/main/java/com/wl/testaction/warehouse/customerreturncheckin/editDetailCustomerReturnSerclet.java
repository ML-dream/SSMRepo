package com.wl.testaction.warehouse.customerreturncheckin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerReturnDetail;
import com.wl.tools.Sqlhelper;

public class editDetailCustomerReturnSerclet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editDetailCustomerReturnSerclet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String sheetId=request.getParameter("sheetId");
		String itemId=request.getParameter("itemId");
		String sql="select sheetid,itemid,itemname,spec,issuenum,unit,returnnum,unitprice,price,qkprice,returnreason," +
				"orderid from customerreturndetail where sheetid='"+sheetId+"' and itemid='"+itemId+"'";
		CustomerReturnDetail customerreturn=new CustomerReturnDetail();
		
		try{
			customerreturn=Sqlhelper.exeQueryBean(sql, null, CustomerReturnDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("customerreturn", customerreturn);
		request.getRequestDispatcher("Checkin/editCustomerReturnDetail.jsp").forward(request, response);
		
	}

}
