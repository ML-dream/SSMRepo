package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ToCustomerProducts extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToCustomerProducts() {
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
		System.out.println(this.getClass().getName());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		
		String bdate =StringUtil.isNullOrEmpty(request.getParameter("bdate"))?"":request.getParameter("bdate");
		String edate =StringUtil.isNullOrEmpty(request.getParameter("edate"))?"":request.getParameter("edate");
		String customer =StringUtil.isNullOrEmpty(request.getParameter("customer"))?"":request.getParameter("customer");
		String rowIndex =StringUtil.isNullOrEmpty(request.getParameter("rowIndex"))?"":request.getParameter("rowIndex");
		String page =StringUtil.isNullOrEmpty(request.getParameter("page"))?"":request.getParameter("page");
		
//		orderId+"&bdate="+bdate+"&edate="+edate+"&customer="+customer+"&rowIndex="+rowIndex+"&page="+page;
//		,DEPT_USER deptUser CUSTOMER,ORDER_STATUS orderStatus,B.companyName,A.connector,A.connectortel,A.orderPaper
		String	orderSql = 
		    	"select ORDER_ID orderId,ORDER_DATE orderDate,endtime,a.staff_name createPerson " +
		    	"from orders t left join employee_info a on t.createperson = a.staff_code " +
		    	"where ORDER_ID=? ";
		String[] params = {orderId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BeanPara bean= new BeanPara();
		try {
			bean.setBdate(bdate);
			bean.setEdate(edate);
			bean.setCustomer(customer);
			bean.setRowIndex(rowIndex);
			bean.setPage(page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String result = "orderStatistics/CustomerProducts.jsp";
//		是否是从工艺返回
		String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");
		if(para.equals("back")){
			result="orderStatistics/CustomerProductsT.jsp";
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("backPara", bean);
//			String test = ((BeanPara)session.getAttribute("backPara")).getBdate();
		}
		request.setAttribute("order", order);
		request.getRequestDispatcher(result).forward(request, response);
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
