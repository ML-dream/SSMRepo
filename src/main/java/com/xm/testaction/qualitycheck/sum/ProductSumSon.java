package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;

public class ProductSumSon extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductSumSon() {
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
//		执行进度统计，子节点点击
		System.out.println(this.getClass().getName());
		String orderId = request.getParameter("orderId");
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String issueNum = request.getParameter("issueNum");
		String productName = ChineseCode.toUTF8(request.getParameter("productName"));
		String productNum = request.getParameter("productNum");
		int temp = 0;	//产品数量
		try {
			temp = Integer.parseInt(productNum);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Order order = new Order();
		order.setOrderId(orderId);
		order.setProductId(productId);
		order.setIssueNum(issueNum);
		order.setProductName(productName);
		order.setProductNum(temp);
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("qualitycheck/processSumDetailB.jsp").forward(request, response);
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
