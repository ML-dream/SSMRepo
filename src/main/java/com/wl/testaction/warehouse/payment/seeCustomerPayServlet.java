package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class seeCustomerPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public seeCustomerPayServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String companyId=request.getParameter("companyId");
		request.setAttribute("companyId", companyId);
		request.getRequestDispatcher("/payMent/seeCustomerPayment.jsp").forward(request, response);
		
		
	}

}
