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

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OrderAllStatusListServlet extends HttpServlet {

	private static final long serialVersionUID = -1160863753819896460L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    
	    String totalCountSql = "select count(*) from orders where createPerson=? ";
	    String[] params1 = {userId};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String sql= "select ORDER_ID orderId,ORDER_DATE orderDate,ENDTIME endTime,CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME,B.connector " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from orders EM where createPerson=? order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;

	    String[] params = {userId};
	    
	    List<Order> orderList = new ArrayList<Order>();
	    try {
			orderList = Sqlhelper.exeQueryList(sql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	
//	    System.out.println("sql=="+sql);
//	    
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(sql, null);
//			List<Order> orderList = new ArrayList<Order>();
//			try {
//				while (OrderRs.next()) {
//					Order order = new Order();
//					order.setOrderId(OrderRs.getString(1));
//					order.setOrderDate(OrderRs.getString(2));
//					order.setEndTime(OrderRs.getString(3));
//					order.setCustomer(OrderRs.getString(4));
//					order.setOrderStatus(OrderRs.getString(5));
//					order.setCompanyName(OrderRs.getString(6));
//					order.setConnector(OrderRs.getString(7));
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













