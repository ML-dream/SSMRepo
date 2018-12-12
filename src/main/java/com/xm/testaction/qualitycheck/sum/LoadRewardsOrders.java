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

public class LoadRewardsOrders extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadRewardsOrders() {
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
//		rewardsPage加载订单
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String bdate = "";
		String edate = "";
		String orderType = "all";
		
		bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bdate:request.getParameter("bdate").trim();
		edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?edate:request.getParameter("edate").trim();
		orderType = StringUtil.isNullOrEmpty(request.getParameter("orderType"))?orderType:request.getParameter("orderType").trim();
		
		String sqla = "";
		String sqlb = "";	//总数
		
//		创建 视图
		RewardsView.rewardsView(bdate, edate);
		
		if(orderType.equals("all")){
//			所有订单或是存在不合格品的订单
			sqla = "select  order_id,order_date,companyname from(" +
					"select  order_id,order_date,companyname,rownum rn from ("+
				"select distinct t.order_id,t.order_date,b.companyname from rewardstemp t " +
					"    left join customer b on b.companyid=t.customer order by order_date asc) ) " +
					"where rn>"+min+" and rn <= "+max;
			sqlb = "select count(*) from (" +
					"select distinct t.order_id from rewardstemp t " +
					")";
		}else{
			sqla = "select  order_id,order_date,companyname( " +
					"select  order_id,order_date,companyname,rownum rn from ("+
			"select distinct t.order_id,t.order_date,b.companyname from rewardstemp t " +
				"    left join customer b on b.companyid=t.customer where t.reject_num > 0 order by order_date asc) )" +
				"where rn>"+min+" and rn <= "+max;
			sqlb = "select count(*) from (" +
				"select distinct t.order_id from rewardstemp t where t.reject_num > 0 " +
				")";
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
