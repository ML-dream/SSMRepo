package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class AllOrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
//	    String orderId=request.getParameter("orderId");
//	    String customer=request.getParameter("customer");
//	    if(orderId==null){
//	    	orderId="";
//	    }
//	    if(customer==null){
//	    	customer="";
//	    }
	    
//	    HttpSession session = request.getSession();
//		String userId = ((User)session.getAttribute("user")).getUserId();
//		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		
//      String totalCountSql = "select count(*) from orders where order_status<11 and order_id like '%"+orderId+"%' and customer like '%"+customer+"%'";
	    String totalCountSql = "select count(*) from orders where order_status<11";
//	    String[] params1 = {staffCode};
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
		
	    String OrderSql= "select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME companyName,D.deptname,B.connector,B.connectorTel " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from orders EM where order_status<11  order by "+orderStr+" asc) " +
	    	"A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
//	    String[] params = {staffCode};
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}

}
