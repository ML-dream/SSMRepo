package com.wl.testaction.orderManage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.tools.ChineseCode;

public class GoOrderPayedListServlet extends HttpServlet {

	private static final long serialVersionUID = -124631613262062646L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		System.out.println("orderId="+orderId);
//		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
//		String itemId = ChineseCode.toUTF8(request.getParameter("itemId"));
		request.setAttribute("orderId", orderId);
//		request.setAttribute("productId", productId);
//		request.setAttribute("itemId", itemId);
	    request.getRequestDispatcher("orderManage/OrderPayedList.jsp").forward(request, response);
	}
}













