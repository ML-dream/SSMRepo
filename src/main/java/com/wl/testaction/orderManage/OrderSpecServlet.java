package com.wl.testaction.orderManage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;

import Test.JSON;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Customer;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class OrderSpecServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println(this.getClass().getName());
	    String orderId = request.getParameter("orderId");
	    String isModify=StringUtil.isNullOrEmpty(request.getParameter("isModify"))?"":request.getParameter("isModify");
//	    xiem 判断是否从资产入库发起请求 2017-2-20
	    String para= StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");
	    
		String	orderSql = 
		    	"select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER," +
		    	"ORDER_STATUS orderStatus,B.companyName,A.connector,A.connectortel,orderPaper,duizhanghan,otherPaper " +
		    	"from orders A left join customer B on B.companyId=A.customer " +
		    	"where ORDER_ID=? ";
		String[] params = {orderId};
		Order order = new Order();
		
		
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssss");
		//System.out.println(order.);
		HttpSession session = request.getSession();
		session.setAttribute("connector",order.getConnector());
		//request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		
		
		/*response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
*/
//		String json = JSON.Encode(order);
		request.setAttribute("order", order);
		request.setAttribute("orderId", orderId);
		request.setAttribute("para", para);
		request.setAttribute("isModify", isModify);
		
//	    System.out.println("orderSql=="+orderSql);
	    
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(orderSql, null);
//			
//			OrderRs.next();
//			Order order = new Order();
//			order.setOrderId(OrderRs.getString(1));
//			order.setDeptUser(OrderRs.getString(2));
//			order.setOrderDate(OrderRs.getString(3));
//			order.setEndTime(OrderRs.getString(4));
//			order.setCustomer(OrderRs.getString(5));
//			order.setOrderStatus(OrderRs.getString(6));
//			order.setCompanyName(OrderRs.getString(7));
//			order.setConnector(OrderRs.getString(8));
//			order.setConnectorTel(OrderRs.getString(9));
//			order.setOrderPaper(OrderRs.getString(10));
//			order.setDuizhanghan(OrderRs.getString(11));
//			order.setOtherPaper(OrderRs.getString(12));
//			
//			request.setAttribute("order", order);
//			
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(OrderRs!=null){
//					OrderRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		if(isModify.equals("0"))
		{
			request.getRequestDispatcher("orderManage/EditOrderSpec.jsp").forward(request, response);
		}
		else if(isModify.equals("1"))
		{
			request.getRequestDispatcher("orderManage/ModifyOrderSpec.jsp").forward(request, response);
		}else if(isModify.equals("2")){
			request.getRequestDispatcher("orderManage/machineOrderToo.jsp").forward(request, response);
			
		}else {
			
			request.getRequestDispatcher("orderManage/bookMainIndex.jsp").forward(request, response);
			}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













