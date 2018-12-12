package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;
/**
 * 查找老版工艺
 * @author xiem
 *
 */
public class DoneFoProduct extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoneFoProduct() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String productName = request.getParameter("productName");
		String productId = request.getParameter("productId");
		
		String sqla ="select * from ("+
			"select t.orderid orderId,b.companyname,t.productid,t.productname,t.issuenum,rownum rn  from foheader t" +
				"  left join orders a on a.order_id = t.orderid " +
				"  left join customer b on b.companyid = a.customer " +
				" where t.productid like '%"+productId+"%' and t.productname like '%"+productName+"%' " +
				")where "+min+ "<rn and rn <=" +max;
		String sqlb = "select count(*) from (" +
				"select t.orderid orderId,t.productid,t.productname,t.issuenum from foheader t" +
				" where t.productid like '%"+productId+"%' and t.productname like '%"+productName+"%' " +
				")";
		int total = 0;
		
		List <Order> lista =new ArrayList<Order> ();
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, Order.class);
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
