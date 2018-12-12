package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class getAddWarehouseCountWarehouseIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getAddWarehouseCountWarehouseIdServlet() {
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
		String warehouseId=request.getParameter("warehouseId");
		String warehouseName="";
		String warehouseSql="select warehouse_name from warehouse where warehouse_id='"+warehouseId+"'";
		try{
			warehouseName=Sqlhelper.exeQueryString(warehouseSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("warehouseId", warehouseId);
		request.setAttribute("warehouseName", warehouseName);
		request.getRequestDispatcher("/WarehouseCount/addWarehouseCountData.jsp").forward(request, response);
		
	}

}
