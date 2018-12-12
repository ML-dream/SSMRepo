package com.wl.testaction.Ll;

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

public class selectProductIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public selectProductIdServlet() {
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
		String orderId=request.getParameter("orderId");
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String[] params={orderId};
		
		
		String totalCountSql="select count(*) from order_detail where order_id=?";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String productSql="select product_id,product_name,spec,issue_num from(select A.*,rownum row_num from" +
				"(select EM.* from order_detail EM where order_id=? order by product_id asc) A where rownum<="+pageSize*pageNow+" " +
						"order by product_id asc) B where row_num >="+(pageSize*(pageNow-1)+1)+"";
		
		List<Order>resultList=new ArrayList<Order>();
		try{
			resultList=Sqlhelper.exeQueryList(productSql, params, Order.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		
		response.getWriter().append(json).flush();
		
	}

}
