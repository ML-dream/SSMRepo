package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class DrawingIdSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String sql="select  product_Name productName,issue_num issueNum,drawingId,unitPrice,spec " +
				    "from order_detail " +
				    "where product_Id=? and issue_num=(select max(issue_Num) from order_detail where product_Id=?)";


  	   String[] params = {productId,productId};
       List<Order> orderList = new ArrayList<Order>();

	    try {
	 
	orderList = Sqlhelper.exeQueryList(sql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
	 
	}

}
