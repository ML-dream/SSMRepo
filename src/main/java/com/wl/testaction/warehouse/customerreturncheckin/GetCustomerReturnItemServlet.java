package com.wl.testaction.warehouse.customerreturncheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.OrderItem;
import com.wl.tools.Sqlhelper;

public class GetCustomerReturnItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCustomerReturnItemServlet() {
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
		request.setCharacterEncoding("utf-8");
		String orderId=request.getParameter("orderId");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String totalCountSql="select count(*) from ORDER_DETAIL where order_id='"+orderId+"'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="select product_id productId,product_name productName,issue_num issueNum,spec,unitprice unitPrice from " +
				"(select A.*,rownum row_num from(select EM.* from order_detail EM where order_id='"+orderId+"' order by product_id) A " +
						"where rownum<="+(pageSize*pageNow)+" order by product_id) B where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<OrderItem> resultList=new ArrayList<OrderItem>();
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, OrderItem.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
	}

}
