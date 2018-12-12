package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class LoadRewardsProducts extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadRewardsProducts() {
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

//		rewardsPage加载订单下产品
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String productType = "all";
		String orderId = "";
		
		productType = StringUtil.isNullOrEmpty(request.getParameter("productType"))?productType:request.getParameter("productType").trim();
		orderId = StringUtil.isNullOrEmpty(request.getParameter("orderId"))?orderId:request.getParameter("orderId").trim();
		
		String sqla = "";
		String sqlb = "";	//总数
		
		
		if(productType.equals("all")){
//			所有订单或是存在不合格品的订单
			sqla = "select distinct orderId,productId,productName from ("+
				"select t.order_id orderId,t.drawingid productId,t.product_name productName,rownum rn from rewardstemp t " +
				"where t.order_id='"+orderId+"' order by productId asc)" +
					"where rn>"+min+" and rn <= "+max;
			sqlb = "select count(*) from (" +
					"select distinct t.drawingid productId from rewardstemp t " +
					"where t.order_id='"+orderId+"'" +
					")";
		}else{
			sqla = "select distinct orderId,productId,productName from ("+
			"select t.order_id orderId,t.drawingid productId,t.product_name productName,rownum rn from rewardstemp t " +
			"where t.reject_num > 0 and t.order_id='"+orderId+"' order by productId asc)" +
				"where rn>"+min+" and rn <= "+max;
			sqlb = "select count(*) from (" +
				"select distinct t.drawingid productId from rewardstemp t where t.reject_num > 0 and t.order_id='"+orderId+"')";
		}
		List<RewardsBean> lista= new ArrayList<RewardsBean>();
		int total = 0;
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, RewardsBean.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			total = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String json = PluSoft.Utils.JSON.Encode(lista);
		json = "{\"total\":"+total+",\"data\":"+json+"}";
		System.out.println(json);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json).flush();
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
