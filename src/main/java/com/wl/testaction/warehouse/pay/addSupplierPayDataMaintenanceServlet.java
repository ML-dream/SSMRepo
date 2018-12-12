package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class addSupplierPayDataMaintenanceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addSupplierPayDataMaintenanceServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String companyId=(String) datamap.get("companyId");
		String maintenanceDate=(String) datamap.get("maintenanceDate");
		String endPayment=(String) datamap.get("endPayment");
		double beginPayment=0;
		double sale=0;
		double thisPayment=0;
		String sql="insert into supplierpaystatistics (maintenancedate,companyid,beginPayment,purchase,thispayment,endpayment) " +
				"values(to_date('"+maintenanceDate+"','yyyy-MM-dd,hh24:mi:ss'),'"+companyId+"','"+beginPayment+"'," +
						"'"+sale+"','"+thisPayment+"','"+endPayment+"')";
		try{
			Sqlhelper.executeUpdate(sql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
