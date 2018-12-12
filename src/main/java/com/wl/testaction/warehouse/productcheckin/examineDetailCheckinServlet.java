package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProductCheckin;
import com.wl.tools.Sqlhelper;

public class examineDetailCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public examineDetailCheckinServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheetId=request.getParameter("checksheetId");
		String checkinSql="select checksheetid,checkindate,orderid,productid,productname,drawingid,issuenum,spec,unit,B.createperson,C.user_name createpersonName,status," +
				"checkinnum,unitprice,lot,qualityid,memo,warehouseid,stockid,deliveryid,whstaffid,D.warehouse_name warehouseName,E.staff_name deliveryName,F.staff_name whstaffName," +
				"producttype,T.item_typedesc productTypeDesc from productcheckin B " +
				"left join sysusers C on C.staff_code=B.createperson " +
				"left join warehouse D on D.warehouse_id=B.warehouseid " +
				"left join employee_info E on E.staff_code=B.deliveryid " +
				"left join employee_info F on F.staff_code=B.whstaffid " +
				"left join itemtype T on T.item_typeid=B.producttype " +
				"where checksheetid='"+checksheetId+"'";
		ProductCheckin productcheckin=new ProductCheckin();
		try{
			productcheckin=Sqlhelper.exeQueryBean(checkinSql, null, ProductCheckin.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("productcheckin", productcheckin);
		request.getRequestDispatcher("Checkin/examineDetailCheckin.jsp").forward(request, response);
		System.out.println(productcheckin);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

}
