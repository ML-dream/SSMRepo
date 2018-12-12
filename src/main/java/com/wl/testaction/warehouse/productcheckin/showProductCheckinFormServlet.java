package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProductCheckin;
import com.wl.tools.Sqlhelper;

public class showProductCheckinFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showProductCheckinFormServlet() {
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
		String checksheetId=request.getParameter("checksheetId");
		String sql="select checksheetid,checkindate,orderid,productid,productname,drawingid,issuenum,spec,unit,B.createperson createpersonId,H.user_name createpersonName," +
				"checkinnum,unitprice,warehouseid,C.warehouse_name warehouseName,stockid,lot,qualityid,memo,status," +
				"deliveryid deliveryId,D.staff_name deliveryName,whstaffid whstaffId,E.staff_name whstaffName,producttype,F.item_typedesc productTypeDesc from productcheckin B " +
				"left join warehouse C on C.warehouse_id=B.warehouseid " +
				"left join employee_info D on D.staff_code=B.deliveryid " +
				"left join employee_info E on E.staff_code=B.whstaffid " +
				"left join itemtype F on F.item_typeid=B.producttype " +
				"left join sysusers H on H.staff_code=B.createperson " +
				" where checksheetid='"+checksheetId+"'";
		ProductCheckin checkin=new ProductCheckin();
		try{
			checkin=Sqlhelper.exeQueryBean(sql, null, ProductCheckin.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(checkin);
		out.append(json).flush();
		System.out.println(json);
	
	}

}
