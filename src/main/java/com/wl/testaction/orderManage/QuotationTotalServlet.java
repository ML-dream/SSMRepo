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

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;

public class QuotationTotalServlet extends HttpServlet {

	private static final long serialVersionUID = -1160863753819896460L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
		System.out.println(orderId);
		String sql = "select distinct product_id ,product_name,fproduct_id,quotationtotal "+
				"from order_detail A "+
				"start with order_id=?"+
				"connect by prior A.product_id=A.fproduct_id";
		
		String[] params1 = {orderId};
		List<Order> orderList = new ArrayList<Order>();
		
		try {
			orderList = Sqlhelper.exeQueryList(sql, params1, Order.class);
//			orderList = Sqlhelper.exeQueryList(sql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    
	    String totalPriceSql = "select sum(M.ProductPrice) from "+
			    " ("+
			    "  select distinct product_id id, product_name text,fproduct_id pid,cengci,quotationTotal ProductPrice " +
			    "from order_detail A start with order_id=? connect by prior A.product_id=A.fproduct_id "+
			    ") M";
	    
	    String[] params2 = {orderId};
	    String OrdertotalPrice = "";
	    
	    try {
			OrdertotalPrice = Sqlhelper.exeQueryString(totalPriceSql, params2);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"OrdertotalPrice\":"+OrdertotalPrice+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	    
//	    ResultSet OrderRs =null;
//	    ResultSet totalPriceRs = null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(sql, null);
//			totalPriceRs = Sqlhelper.executeQuery(totalPrice, null);
//			
//			List<Order> orderList = new ArrayList<Order>();
//			try {
//				while (OrderRs.next()) {
//					Order order = new Order();
//					order.setProductId(OrderRs.getString(1));
//					order.setProductName(OrderRs.getString(2));
//					order.setQuotationTotal(OrderRs.getDouble(4));
//					order.setFproductId(OrderRs.getString(3));
//					
//					orderList.add(order);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			totalPriceRs.next();
//			double OrdertotalPrice = 0;
//			OrdertotalPrice= totalPriceRs.getDouble(1);
//			String json = PluSoft.Utils.JSON.Encode(orderList);
//			json = "{\"OrdertotalPrice\":"+OrdertotalPrice+",\"data\":"+json+"}";
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













