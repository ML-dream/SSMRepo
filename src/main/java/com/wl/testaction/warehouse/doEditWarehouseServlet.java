package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doEditWarehouseServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String warehouse_id=(String) datamap.get("warehouse_id");
		String warehouse_name=(String) datamap.get("warehouse_name");
		String shelf_num=(String) datamap.get("shelf_num");
		String shelf_storey=(String) datamap.get("shelf_storey");
		String shelf_column=(String) datamap.get("shelf_column");
		String wh_addr=(String) datamap.get("wh_addr");
		String wh_phone=(String) datamap.get("wh_phone");
		String emp_id=(String) datamap.get("emp_id");
		String sql="update warehouse set warehouse_id='"+warehouse_id+"',warehouse_name='"+warehouse_name+"'," +
				"wh_addr='"+wh_addr+"',wh_phone='"+wh_phone+"',emp_id='"+emp_id+"',shelf_num="+shelf_num+",shelf_storey="+shelf_storey+",shelf_column="+shelf_column+" where warehouse_id='"+warehouse_id+"'";
		try{
			
			Sqlhelper.executeUpdate(sql, null);
			String json = "{\"result\":\"操作成功!\"}";		
			response.getWriter().append(json).flush();
			
			}catch(Exception e){
				String json = "{\"result\":\"操作失败!\"}";
				response.getWriter().append(json).flush();
				e.printStackTrace();
				
			}
	}

}
