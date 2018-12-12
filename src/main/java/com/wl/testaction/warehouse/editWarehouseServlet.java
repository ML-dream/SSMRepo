package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;

public class editWarehouseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String warehouseid=request.getParameter("warehouseid");
		System.out.println("id="+warehouseid);
		String sql="select warehouse_id,warehouse_name,shelf_num,shelf_storey,shelf_column,wh_addr,wh_phone,emp_id from warehouse where warehouse_id=?";
		//ResultSet rs=null;
		String[] params = {warehouseid};
		Warehouse warehouse=new Warehouse();
		try{
			
			warehouse = Sqlhelper.exeQueryBean(sql, params,  Warehouse.class);
			
			/*rs=Sqlhelper.executeQuery(sql, null);
			while(rs.next()){
				Warehouse warehouse=new Warehouse();
				warehouse.setWarehouse_id(rs.getString(1));
				warehouse.setWarehouse_name(rs.getString(2));
				warehouse.setShelf_num(rs.getInt(3));
				warehouse.setShelf_storey(rs.getInt(4));
				warehouse.setShelf_column(rs.getInt(5));
				
				warehouse.setWh_addr(rs.getString(6));
				warehouse.setWh_phone(rs.getString(7));
				warehouse.setEmp_id(rs.getString(8));
				request.setAttribute("warehouse", warehouse);
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("warehouse", warehouse);
		request.getRequestDispatcher("/warehouseDefi/editWarehouse.jsp").forward(request, response);
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

		this.doGet(request, response);
		
	}

}
