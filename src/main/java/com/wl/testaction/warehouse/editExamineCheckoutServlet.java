package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.Checkout;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class editExamineCheckoutServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editExamineCheckoutServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheet_id");
		
//		HttpSession session=request.getSession();
//		String editPerson=((User) session.getAttribute("user")).getStaffCode();
//		String createSql="select examineperson from mycheckout where checksheet_id='"+checksheet_id+"'";
//		Checkout examinePerson=new Checkout();
//		try{
//			examinePerson=Sqlhelper.exeQueryBean(createSql, null, Checkout.class);
//			staffCode=examinePerson.getExaminePerson();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		
//		String sql="select CHECKOUT_DATE,CHECKSHEET_ID,COMPANYNAME,B.WAREHOUSE_ID,C.WAREHOUSE_NAME warehouseName,CONNECTOR,OPINION," +
// 		"CONNECTORTEL,DELIVERY,D.staff_name deliveryName,OPERATOR,E.staff_name operatorName,ORDER_ID,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME from " +
// 		"mycheckout B left join warehouse C on C.warehouse_id=B.warehouse_id " +
// 		"left join EMPLOYEE_INFO D on D.staff_code=B.delivery " +
// 		"left join EMPLOYEE_INFO E on E.staff_code=B.operator " +
// 		"where checksheet_id='"+checksheet_id+"'";
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
		request.getRequestDispatcher("/Checkout/editExamineCheckout.jsp").forward(request, response);
		
		}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
