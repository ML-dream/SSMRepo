package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkout;
import com.wl.tools.Sqlhelper;

public class printCheckoutServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public printCheckoutServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String sheetId=request.getParameter("sheetId");
		String sql="select CHECKOUT_DATE,CHECKSHEET_ID,B.COMPANYID,F.COMPANYNAME companyname,B.WAREHOUSE_ID,C.WAREHOUSE_NAME warehouseName,F.CONNECTOR,OPINION,STATUS," +
 		"F.CONNECTORTEL,DELIVERY,D.staff_name deliveryName,B.ORDER_ID,CHECKOUTTYPE checkoutType,G.CHECKOUTTYPEDESC chenkoutTypeDesc,E.ORDER_STATUS orderStatus,H.ORDERSTATUSDESC orderStatusDesc,B.CREATEPERSON,B.CREATETIME,B.CHANGEPERSON,B.CHANGETIME from " +
 		"mycheckout B left join warehouse C on C.warehouse_id=B.warehouse_id " +
 		"left join EMPLOYEE_INFO D on D.staff_code=B.delivery " +
 		"left join ORDERS E on E.order_id=B.order_id " +
 		"left join CUSTOMER F on F.companyid=B.companyid " +
 		"left join checkouttype G on G.checkouttypeid=B.checkouttype " +
 		"left join orderstatus H on H.orderstatusid=E.ORDER_STATUS " +
 		"where checksheet_id='"+sheetId+"'";
		Checkout checkout=new Checkout();
		
		try{
				checkout=Sqlhelper.exeQueryBean(sql, null, Checkout.class);
    			request.setAttribute("checkout", checkout);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Checkout/printExamineCheckout.jsp").forward(request, response);
		
		
		
	}

}
