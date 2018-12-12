package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;

public class CustomerOrdersCost extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CustomerOrdersCost() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		doPost(request, response);
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

//		综合查询，客户下的订单成本计算
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		String bdate = request.getParameter("bdate");
		String edate = request.getParameter("edate");
		String companyId = request.getParameter("customer");
		
//		查询订单
		String sqla = "select t.order_id from orders t " +
		"where t.customer ='"+companyId+"' and t.order_date between to_date('"+bdate+"','yyyy-MM-ddHH:mi:ss') and to_date('"+edate+"','yyyy-MM-ddHH:mi:ss')";
//		计算订单下各产品的成本
		OrderCostView.oneOrderDetail(sqla);
		String result = "success";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(result).flush();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
