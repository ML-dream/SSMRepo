package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class addCustomerPaymentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addCustomerPaymentServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String addDate=(String) datamap.get("addDate");
		String companyId=(String) datamap.get("companyId");
		String initialPayment=(String) datamap.get("initialPayment");
		
		String Sql="insert into INITIALCUSTOMERPAYMENT (adddate,companyid,initialpayment) values " +
				"(to_date('"+addDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+companyId+"','"+initialPayment+"')";
		try{
			Sqlhelper.executeUpdate(Sql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			response.getWriter().append(json).flush();
		}
		
	
	
	}

}
