package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ProductSum extends HttpServlet {
// 统计树 跳转
	/**
	 * Constructor of the object.
	 */
	public ProductSum() {
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
//		树跳转到 订单零件计划下
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		request.setAttribute("orderId", orderId);
		
		System.out.println(this.getClass().getName());
		String	orderSql = 
		    	"select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME endTime,CUSTOMER,ORDER_STATUS orderStatus,B.companyName,A.connector,A.connectortel,confirmAdvice,orderPaper,duizhanghan,otherPaper " +
		    	"from orders A left join customer B on B.companyId=A.customer " +
		    	"where ORDER_ID=?";
		String[] params = {orderId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("qualitycheck/orderToPartplan.jsp").forward(request, response);
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
