package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.wl.common.ProductStatus;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OrderPayedListServlet extends HttpServlet {

	private static final long serialVersionUID = -1160863753819896460L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
		
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		String sql = "select order_id orderId,product_id productId,fproduct_id fproductId,product_name productName,drawingid,issue_num issueNum," +
				"PURDUCT_NUM productNum,b_time bTime,e_time eTime,finishNum,PRODUCT_STATUS productStatus,alreadypaynum " +
				"from order_detail  where order_id=? and createPerson=? ";
		String[] params = {orderId,staffCode};
		
		List<Order> orderList = new ArrayList<Order>();
		try {
			orderList = Sqlhelper.exeQueryList(sql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0,l=orderList.size();i<l;i++)
		{ Order order=orderList.get(i);
		  if ((order.getProductStatus()>0)&&(order.getProductStatus()<=60))
				  {
			        order.setProductStatus(ProductStatus.DOING);
			        System.out.println(order.getProductStatus());
				  }
			
		}

	
		String json = PluSoft.Utils.JSON.Encode(orderList);
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
//					order.setProductId(OrderRs.getString(2));
//					order.setFproductId(OrderRs.getString(3));
//					order.setProductName(OrderRs.getString(4));
//					order.setDrawingId(OrderRs.getString(5));
//					order.setIssueNum(OrderRs.getString(6));
//					order.setProductNum(OrderRs.getInt(7));
//					order.setWillPayNum((int)(OrderRs.getDouble(7)*1.05+1));
//					order.setbTime(OrderRs.getString(8));
//					order.seteTime(OrderRs.getString(9));
//					order.setFinishNum(OrderRs.getString(10));
//					order.setOrderStatus(OrderRs.getString(11));
//					order.setAlreadyPayNum(OrderRs.getInt(12));
//					
//					orderList.add(order);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(orderList);
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













