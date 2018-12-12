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

import com.wl.tools.StringUtil;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Customer;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class ShowOrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "PRODUCT_ID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    
	    String totalCountSql = "select count(*) from order where createPerson=? ";
	    String[] params1 = {userId};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    String OrderSql= "select ORDER_ID,DEPT_USER,ORDER_DATE,ENDTIME,CUSTOMER,ORDER_STATUS,C.COMPANYNAME,D.deptname " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from orders EM where createPerson=? order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    String[] params2 = {userId};
	    List<Order> orderList = new ArrayList<Order>();
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, params2, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	
//	    System.out.println("OrderSql=="+OrderSql);
//	    
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(OrderSql, null);
//			List<Order> orderList = new ArrayList<Order>();
//			try {
//				while (OrderRs.next()) {
//					Order order = new Order();
//					order.setOrderId(OrderRs.getString(1));
//					order.setDeptUser(OrderRs.getString(2));
//					order.setOrderDate(OrderRs.getString(3));
//					order.setEndTime(OrderRs.getString(4));
//					order.setCustomer(OrderRs.getString(5));
//					order.setOrderStatus(OrderRs.getString(6));
//					order.setCompanyName(OrderRs.getString(7));
//					order.setDeptName(OrderRs.getString(8));
//					
//					orderList.add(order);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(orderList);
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//			e.printStackTrace();
//		}  finally{
//			try {
//				if(OrderRs!=null){
//					OrderRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













