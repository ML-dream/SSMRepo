package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class saveSupplierPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public saveSupplierPayServlet() {
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
		String payDate=(String) datamap.get("payDate");
		String thispaid=(String) datamap.get("thispaid");
		String qualitykk=(String) datamap.get("qualitykk");
		String memo=(String) datamap.get("memo");
		//String tax=(String) datamap.get("tax");
		double Qualitykk=StringUtil.isNullOrEmpty(qualitykk)?0:Double.parseDouble(qualitykk);
		double thisPaid=StringUtil.isNullOrEmpty(thispaid)?0:Double.parseDouble(thispaid);
		
		String rateSql="select rate from supplier where companyid='"+companyId+"'";
		String supplierRate="";
		try{
			supplierRate=Sqlhelper.exeQueryString(rateSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		double rate=StringUtil.isNullOrEmpty(supplierRate)?0:Double.parseDouble(supplierRate);
		
		double tax=StringUtil.isNullOrEmpty((String)datamap.get("tax"))?thisPaid*rate:Double.parseDouble((String)datamap.get("tax"));
		String sql="insert into supplierpayment values('"+companyId+"',to_date('"+payDate+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+thisPaid+"','"+Qualitykk+"','"+tax+"','"+memo+"')";
		try{
			Sqlhelper.executeUpdate(sql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			e.printStackTrace();
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			response.getWriter().append(json).flush();
		}
		
	}

}
