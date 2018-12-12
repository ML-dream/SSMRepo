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
import com.wl.forms.OrderPayed;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OrderPayedDetailListServlet extends HttpServlet {
	private static final long serialVersionUID = 4365688127954856432L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "orderId";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String productId =  ChineseCode.toUTF8(request.getParameter("productId").trim());
	    String fproductId =  ChineseCode.toUTF8(request.getParameter("fproductId").trim());
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    
	    String totalCountSql = "select count(*) from orderpayed " +
	    		"where orderId=? and productId=? and FProductId=? ";
	    
	    String[] params1 = {orderId,productId,fproductId};
	    
	    try {
			totalCount =Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String OrderSql= "select payedNum,payedTime,payedPerson,od.product_name productName " +
	    	"from (select A.*,ROWNUM row_num from (" +
	    		"select EM.* from orderpayed EM where EM.orderId=? and EM.productId=? and EM.FProductId=? order by "+orderStr+" asc) A " +
	    	"where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join  order_detail od on od.order_id=B.orderid and od.product_id=B.productid and od.fproduct_id=B.fproductid "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    String[] params = {orderId,productId,fproductId};
	    List<OrderPayed> orderList = new ArrayList<OrderPayed>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, params, OrderPayed.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0,len=orderList.size(); i < len; i++) {
			OrderPayed orderPayed = orderList.get(i);
			orderPayed.setOrderId(orderId);
			orderPayed.setProductId(productId);
			orderPayed.setFProductId(fproductId);
		}
	    
		String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
//	    System.out.println("OrderSql=="+OrderSql);
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(OrderSql, null);
//			List<OrderPayed> orderList = new ArrayList<OrderPayed>();
//			try {
//				while (OrderRs.next()) {
//					OrderPayed orderPayed = new OrderPayed();
//					orderPayed.setOrderId(orderId);
//					orderPayed.setProductId(productId);
//					orderPayed.setFProductId(fproductId);
//					orderPayed.setPayedNum(OrderRs.getInt(1));
//					orderPayed.setPayedTime(OrderRs.getString(2));
//					orderPayed.setPayedPerson(OrderRs.getString(3));
//					orderPayed.setProductName(OrderRs.getString(4));
//					
//					orderList.add(orderPayed);
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