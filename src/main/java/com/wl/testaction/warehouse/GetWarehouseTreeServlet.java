package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.OrderTree;
import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;

public class GetWarehouseTreeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetWarehouseTreeServlet() {
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
		PrintWriter out = response.getWriter();
		String sql="select warehouse_id,warehouse_name from warehouse order by warehouse_id";
		List<Warehouse> WarehouseTreeList=new ArrayList<Warehouse>();
		try {
			WarehouseTreeList = Sqlhelper.exeQueryList(sql, null, Warehouse.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringBuffer jsonBuffer = new StringBuffer(8192);
	    jsonBuffer.append("[");
	    for (int i = 0,len=WarehouseTreeList.size(); i < len; i++) {
	    	Warehouse warehouse = WarehouseTreeList.get(i);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+warehouse.getWarehouseid()+"\",");
			jsonBuffer.append("\"pid\":"+"\"0000\",");
			jsonBuffer.append("\"level\":"+"\"1\",");		//1：库方层
			jsonBuffer.append("\"warehouseId\":"+"\""+warehouse.getWarehouseid()+"\",");
			jsonBuffer.append("\"text\":"+"\""+warehouse.getWarehousename()+"\"");
			jsonBuffer.append("},");
	    }
			String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(jsonString).flush();
			System.out.println(jsonString);
	}

}
