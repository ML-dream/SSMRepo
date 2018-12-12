package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doshowExamineProductCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doshowExamineProductCheckinServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String checksheetId=(String) datamap.get("checksheetId");
		String productId=(String) datamap.get("productId");
		String warehouseId=(String) datamap.get("warehouseId");
		String stockId=(String) datamap.get("stockId");
		String deliveryId=(String) datamap.get("deliveryId");
		String whstaffId=(String) datamap.get("whstaffid");
		String memo=(String) datamap.get("memo");
		String CheckinSql="update productcheckin set warehouseid='"+warehouseId+"',stockid='"+stockId+"'," +
				"deliveryid='"+deliveryId+"',whstaffid='"+whstaffId+"',memo='"+memo+"' where checksheetid='"+checksheetId+"'";
		String StockSql="update stock set warehouse_id='"+warehouseId+"',stockid='"+stockId+"' where item_id='"+productId+"'";
		try{
			Sqlhelper.executeUpdate(CheckinSql, null);
			Sqlhelper.executeUpdate(StockSql, null);
			String json="{\"result\":\"修改保存成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"修改保存失败！\"}";
			out.append(json).flush();
			e.printStackTrace();
		}
	}

}
