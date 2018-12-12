package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.HelpExcelOut;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ProductsForSaler extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductsForSaler() {
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
		
		int totalCount = 0;
		String conditon = HelpExcelOut.conditonSql(request, response);
		if(!conditon.equals("noEntry")){
			String totalCountSql = "select count(*) from orders t " +
			"left join order_detail p on  p.order_id=t.order_id "+conditon;
			
	
			try {
				totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		List<Order> orderList = new ArrayList<Order>();
		orderList= HelpExcelOut.productSaleOut(request, response);
		String remind="0";
		String json = PluSoft.Utils.JSON.Encode(orderList);
		String mark = "0";
		
		if(orderList ==null){
			remind="当前权限只允许查看本人订单";
			mark="1";
		}
		
	   
	    json = "{\"mark\":"+mark+",\"remind\":\""+remind+"\",\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
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
