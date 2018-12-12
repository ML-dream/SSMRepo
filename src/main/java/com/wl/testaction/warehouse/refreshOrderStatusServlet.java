package com.wl.testaction.warehouse;

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

public class refreshOrderStatusServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public refreshOrderStatusServlet() {
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
		
//		String refreshSql="select d.orderId from orderCheckOut d where d.outNum>=d.productNum group by d.orderId";
//		
//		List<Order> orderList=new ArrayList<Order>();
//		String orderStatus="11";
//		
//		try {
//			orderList=Sqlhelper.exeQueryList(refreshSql, null, Order.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		
//		for(int i=0,l=orderList.size();i<l;i++){
//			Order orderBean=new Order();
//			orderBean=orderList.get(i);
//			String orderId=orderBean.getOrderId();
//			String[] params={orderStatus,orderId};
//			String updateSql="update orders set order_status=? where order_id=?";
//			try {
//				Sqlhelper.executeUpdate(updateSql, params);
//			} catch (Exception e) {
//				e.printStackTrace();
//				// TODO: handle exception
//			}
//				
//		}
		
		String sql= "update orders a set a.order_status='11' where a.order_id in( select t.orderId from ordercheckout t where t.outNum>t.productNum group by t.orderId)";
		try {
			Sqlhelper.executeUpdate(sql, null);
			System.out.print("ok "+sql );
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json="{\"result\":\"订单状态更新成功！\"}";
		response.getWriter().append(json).flush();
		
	}

}
