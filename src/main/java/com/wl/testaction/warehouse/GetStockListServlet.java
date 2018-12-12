package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;

public class GetStockListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetStockListServlet() {
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
		String warehouse_id=request.getParameter("warehouse_id");
		String sql="select warehouse_name from warehouse where warehouse_id='"+warehouse_id+"'";
		String warehouseName="";
		Warehouse warehouse=new Warehouse();
		try{
			warehouse=Sqlhelper.exeQueryBean(sql, null, Warehouse.class);
			warehouseName=warehouse.getWarehousename();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("warehouse_id", warehouse_id);
		request.setAttribute("warehouseName", warehouseName);
		request.getRequestDispatcher("Stock/StockList.jsp").forward(request, response);
		
	}

}
