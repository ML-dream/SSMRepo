package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;

public class PrintItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrintItemServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String orderId=request.getParameter("orderId");
		
		String Sql="select customer, B.companyname from orders A " +
				"left join customer B on B.companyid=A.customer " +
				"where order_id='"+orderId+"'";
		Order order=new Order();
		
		try{
			order=Sqlhelper.exeQueryBean(Sql, null, Order.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("orderId", orderId);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/PO/printItem.jsp").forward(request, response);
	}

}
