package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerAssist;
import com.wl.tools.Sqlhelper;

public class showCustomerCheckinFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showCustomerCheckinFormServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checkSheetid=request.getParameter("checkSheetid");
		
		String customerSql="select checkindate,checksheetid,orderid,customerid,customername,itemid,itemname," +
		"itemtypeid,spec,checkinnum,price,warehouseid,stockid,lot,qualityid,memo,deliveryid,examineid,whstaffid," +
		"C.item_typedesc itemTypeDesc,D.warehouse_name warehouseName,E.staff_name deliveryName,F.staff_name examineName," +
		"T.staff_name whstaffName from customercheckin B " +
		"left join itemtype C on C.item_typeid=B.itemtypeid " +
		"left join warehouse D on D.warehouse_id=B.warehouseid " +
		"left join employee_info E on E.staff_code=B.deliveryid " +
		"left join employee_info F on F.staff_code=B.examineid " +
		"left join employee_info T on T.staff_code=B.whstaffid " +
		"where checksheetid='"+checkSheetid+"'";
		CustomerAssist customercheckin=new CustomerAssist();
		
		try{
			customercheckin=Sqlhelper.exeQueryBean(customerSql, null, CustomerAssist.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(customercheckin);
		out.append(json).flush();
		System.out.println(json);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
