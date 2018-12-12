package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.Checkin;
import com.wl.forms.Checkout;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class editCheckoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheet_id");
		
		HttpSession session=request.getSession();
		String editPerson=((User) session.getAttribute("user")).getStaffCode();
		String createSql="select createPerson from mycheckout where checksheet_id='"+checksheet_id+"'";
		Checkout createPerson=new Checkout();
		String staffCode="";
		try{
			createPerson=Sqlhelper.exeQueryBean(createSql, null, Checkout.class);
			staffCode=createPerson.getCreatePerson();
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		if(editPerson.equals(staffCode)){
		String sql="select CHECKOUT_DATE,CHECKSHEET_ID,B.COMPANYID,F.COMPANYNAME companyname,B.WAREHOUSE_ID,C.WAREHOUSE_NAME warehouseName,F.CONNECTOR,OPINION,STATUS," +
 		"F.CONNECTORTEL,DELIVERY,D.staff_name deliveryName,B.ORDER_ID,CHECKOUTTYPE checkoutType,G.CHECKOUTTYPEDESC chenkoutTypeDesc,E.ORDER_STATUS orderStatus,H.ORDERSTATUSDESC orderStatusDesc,B.CREATEPERSON,B.CREATETIME,B.CHANGEPERSON,B.CHANGETIME from " +
 		"mycheckout B left join warehouse C on C.warehouse_id=B.warehouse_id " +
 		"left join EMPLOYEE_INFO D on D.staff_code=B.delivery " +
 		"left join ORDERS E on E.order_id=B.order_id " +
 		"left join CUSTOMER F on F.companyid=B.companyid " +
 		"left join checkouttype G on G.checkouttypeid=B.checkouttype " +
 		"left join orderstatus H on H.orderstatusid=E.ORDER_STATUS " +
 		"where checksheet_id='"+checksheet_id+"'";
		Checkout checkout=new Checkout();
		
		try{
			checkout=Sqlhelper.exeQueryBean(sql, null, Checkout.class);
			request.setAttribute("checkout", checkout);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Checkout/editCheckout.jsp").forward(request, response);
		
		}
//	else{
//			request.getRequestDispatcher("Fail.jsp").forward(request, response);
//		}
//	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
		
	}

}
