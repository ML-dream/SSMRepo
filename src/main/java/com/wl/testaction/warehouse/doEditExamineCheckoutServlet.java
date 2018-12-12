package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doEditExamineCheckoutServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doEditExamineCheckoutServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request,response);
	}

	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String, String>) Test.JSON.Decode(data);
		String warehouse_id=datamap.get("warehouse_id");
		String operator=datamap.get("operator");
		String opinion=datamap.get("opinion");
		String checksheet_id=datamap.get("checksheet_id");
		String Sql="update mycheckout set warehouse_id='"+warehouse_id+"',operator='"+operator+"'," +
				"opinion='"+opinion+"' where checksheet_id='"+checksheet_id+"'";
		try{
			Sqlhelper.executeUpdate(Sql, null);
			String json="{\"result\":\"操作成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			out.append(json).flush();
			e.printStackTrace();
		}
	
	}

}
