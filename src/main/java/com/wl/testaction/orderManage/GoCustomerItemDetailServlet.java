package com.wl.testaction.orderManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.tools.ChineseCode;

public class GoCustomerItemDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 4683029523363435969L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String customerId = ChineseCode.toUTF8(request.getParameter("customerId").trim());
		String customerName = ChineseCode.toUTF8(request.getParameter("customerName").trim());
		//String connector = ChineseCode.toUTF8(request.getParameter("connector").trim());
		request.setAttribute("orderId", orderId);
		request.setAttribute("customerId", customerId);
		request.setAttribute("customerName", customerName);
		//request.setAttribute("connector", connector);
	    request.getRequestDispatcher("orderManage/CustomerItemDetailList.jsp").forward(request, response);
	}


}
