package com.wl.testaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoProcessPlanServ extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    System.out.println("进入GoProcessPlanServ函数体了已经！！！");

	    String product_type = request.getParameter("product_type");
	  	System.out.println("==================================================="+product_type);
		
	  	request.setAttribute("product_type", product_type);
		this.getServletConfig().getServletContext().getRequestDispatcher("demo/tree/navtree.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
