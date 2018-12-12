package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.HelpExcelOut;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ProductSaleExcelOut extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductSaleExcelOut() {
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
		
		List<Order> orderList = new ArrayList<Order>();
		orderList = HelpExcelOut.productSaleOut(request, response);
		
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		
		liebiaoxiang.put("orderId", "订单编号");
		liebiaoxiang.put("productId", "图号");
		liebiaoxiang.put("productName", "名称");
		liebiaoxiang.put("batch", "批号");
		liebiaoxiang.put("unitprice", "单价");
		
		liebiaoxiang.put("productNum", "数量");
		liebiaoxiang.put("productPrice", "金额");
		liebiaoxiang.put("Productremark", "备注");
		
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, orderList);
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
