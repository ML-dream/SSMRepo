package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckinDetl;
import com.wl.forms.CheckoutDetl;
import com.wl.tools.Sqlhelper;

public class editDetailCheckoutServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheet_id");
		String itemId=request.getParameter("itemId");
		String issueNum=request.getParameter("issueNum");
		String warehouse_id=request.getParameter("warehouse_id");
		String order_id=request.getParameter("order_id");
		String sql="select checksheet_id,item_id,item_name,spec,stocknum,checkout_num,unitprice,price,stock_id," +
		"memo,B.issue_num,item_type,alreadypaynum,nopaynum,purductnum,drawingid,C.item_typedesc itemTypeDesc from mycheckout_detl B " +
		"left join itemtype C on C.item_typeid=B.item_type " +
		"where checksheet_id='"+checksheet_id+"' and item_id='"+itemId+"'";
		System.out.println("sql="+sql);
		
		CheckoutDetl checkoutdetl=new CheckoutDetl();
		try{
			checkoutdetl=Sqlhelper.exeQueryBean(sql, null, CheckoutDetl.class);
			request.setAttribute("checkoutdetl", checkoutdetl);
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("order_id", order_id);
		request.setAttribute("warehouse_id", warehouse_id);
		request.getRequestDispatcher("Checkout/editDetailCheckout.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 
	}

}
