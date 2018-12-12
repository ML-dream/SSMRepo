package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class DeleteOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteOrder() {
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
//		撤销订单
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		String orderId = request.getParameter("orderId");
		String json = "";
		String result = "";
//		拷贝到flash表备份
		String sqlb = "insert into orderflash (select * from orders t where t.order_id = '"+orderId+"');";
		String sqlc = "insert into order_detailflash(select * from order_detail a where a.order_id='"+orderId+"' );";
	
//		从order表与order_detail表里删除
		String sqla = "delete  from orders t where t.order_id='"+orderId+"';";
		String sqld = "delete  from order_detail t where t.order_id='"+orderId+"';";
		
		String sql = "begin "+sqlb +sqlc + sqla + "end;";
		try {
			System.out.println(sql);
			Sqlhelper.executeUpdate(sql, null);
			System.out.println("ok  "+sql);
			result = "操作成功";
		} catch (Exception e) {
			// TODO: handle exception
			result = "操作失败";
		}
		json = "{\"result\":\""+result+"\"}";
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
