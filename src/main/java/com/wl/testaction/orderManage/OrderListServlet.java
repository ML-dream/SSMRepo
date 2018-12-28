package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wl.tools.StringUtil;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OrderListServlet extends HttpServlet {

	private static final long serialVersionUID = -3196500925146346715L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	   //bdate:bdate, edate:edate , customer:customer
	    
	    
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	   /* 
	    String  bdate = request.getParameter("bdate");
	    String  edate = request.getParameter("edate");*/
	    //String  customer = request.getParameter("customer");
	    
	    
	    String bdate=StringUtil.isNullOrEmpty(request.getParameter("bdate"))?"":request.getParameter("bdate");
	    String edate=StringUtil.isNullOrEmpty(request.getParameter("edate"))?"":request.getParameter("edate");
	    
	    
	    String orderId=StringUtil.isNullOrEmpty(request.getParameter("orderId"))?"":request.getParameter("orderId");
	    String customer=StringUtil.isNullOrEmpty(request.getParameter("customer"))?"":request.getParameter("customer");

	    //	    String companyId=request.getParameter("companyId");
	   
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		
	    String totalCountSql = "select count(*) from orders B left join customer C on B.CUSTOMER=C.COMPANYID  where order_status<11 and order_id like '%"+orderId+"%' and companyName like '%"+customer+"%'";
//	    String[] params1 = {staffCode};
	    
//	    xiem 是否查询未交付完成订单  3表示全部订单
	    String orderMode = StringUtil.isNullOrEmpty(request.getParameter("orderMode"))?"3":request.getParameter("orderMode");
	   
	    
		
	    String OrderSql= "select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER,ORDER_STATUS orderStatus,book_status bookStatus,C.COMPANYNAME companyName,D.deptname,B.connector,B.connectorTel " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from orders EM where order_status<11 and order_id like '%"+orderId+"%' and createperson ='"+staffCode +"'  order by ORDER_DATE desc) " +
	    	"A where ROWNUM<="+(countPerPage*pageNo)+" order by ORDER_DATE desc) B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+"  and  C.companyName like '%"+customer+"%' order by ORDER_DATE desc";
	    
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













