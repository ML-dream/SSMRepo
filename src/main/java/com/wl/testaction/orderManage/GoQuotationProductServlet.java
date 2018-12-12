package com.wl.testaction.orderManage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;


public class GoQuotationProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 237629340050823527L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
		String name = ChineseCode.toUTF8(request.getParameter("name"));
		request.setAttribute("orderId", orderId);
		request.setAttribute("productId", productId);
		request.setAttribute("fproductId", fproductId);
		request.setAttribute("name", name);
	    request.getRequestDispatcher("orderManage/QuotationEdit.jsp").forward(request, response);
	}
}













