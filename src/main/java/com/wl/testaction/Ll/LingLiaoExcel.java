package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.HelpExcelOut;

public class LingLiaoExcel extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LingLiaoExcel() {
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
		
		List<BeanLingLiaoSum> orderList = new ArrayList<BeanLingLiaoSum>();
		orderList = HelpSumExcel.lingLiaoOut(request, response);
		
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		
		liebiaoxiang.put("productName", "产品");
		liebiaoxiang.put("productId", "图号");
		liebiaoxiang.put("customer", "客户");
		liebiaoxiang.put("customerId", "客户编号");
		liebiaoxiang.put("productNum", "产品数量");
		
		liebiaoxiang.put("orderId", "订单号");
		liebiaoxiang.put("liDate", "领料日期");
		liebiaoxiang.put("stuff", "材料");
		liebiaoxiang.put("stuff", "材料");
		liebiaoxiang.put("spec", "规格");
		liebiaoxiang.put("unit", "材料");
		
		liebiaoxiang.put("liNum", "领料数量");
		liebiaoxiang.put("unitPrice", "单价");
		liebiaoxiang.put("sumPrice", "合计");
		liebiaoxiang.put("itemType", "物料类型");
		liebiaoxiang.put("sheetId", "领料单");
		
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
