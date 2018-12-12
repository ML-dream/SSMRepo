package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerItem;
import com.wl.forms.SheetId;
import com.wl.tools.CheckSheetId;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class SeeCustomerItemSpecServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CheckSheetId checksheetid=new CheckSheetId();
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String mainId=ChineseCode.toUTF8(request.getParameter("mainId").trim());
		
//		String orderId=ChineseCode.toUTF8(request.getParameter("id").trim());
//		String customerId=ChineseCode.toUTF8(request.getParameter("customer").trim());
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=df.format(new Date());
		String orderSql = "select mainid,orderid,customeId,itemId,itemname," +
			    	"itemspec,starttime,endtime," +
			    	"person,memo,itemType,itemNum," +
			    	"itemPrice,isCheckIn,unit,customeName,B.staff_name personName " +
			    	"from customeasset A " +
			    	"left join employee_info B on B.staff_code=A.person " +
			    	"where orderId=? and mainId=?";
		String[] params = {orderId,mainId};
		SheetId checksheet_id=checksheetid.getCustomerCheckinSheetid();
		CustomerItem customerItem = new CustomerItem();
		try {
			customerItem = Sqlhelper.exeQueryBean(orderSql, params, CustomerItem.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		customerItem.setCustomeId(customerId);
		request.setAttribute("checksheet_id", checksheet_id);
		request.setAttribute("result", customerItem);
		request.setAttribute("createTime", createTime);
	    request.getRequestDispatcher("Checkin/CustomerItemSpec.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request,response);
	}

}
