package com.wl.testaction.warehouse.customerreturncheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doEditCustomerReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doEditCustomerReturnServlet() {
		super();
	}

	
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
		String sheetId=(String) datamap.get("sheetId");
		String returnDate=(String) datamap.get("date");
		String orderId=(String) datamap.get("orderId");
		String companyId=(String) datamap.get("companyId");
		String connector=(String) datamap.get("connector");
		String connectorTel=(String) datamap.get("connectorTel");
		
		String sql="update customerreturn set returndate=to_date('"+returnDate+"','yyyy-mm-dd,hh24:mi:ss'),orderid='"+orderId+"'," +
				"companyId='"+companyId+"',connector='"+connector+"',connectortel='"+connectorTel+"' where sheetid='"+sheetId+"'";
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
