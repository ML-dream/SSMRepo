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

import com.wl.forms.Order;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class OrderForDelete extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderForDelete() {
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

//		订单删除页面的订单加载。
		System.out.println(this.getClass().getName());
		
		String orderId= "";
		orderId = StringUtil.isNullOrEmpty(request.getParameter("orderId"))?orderId:request.getParameter("orderId").trim();
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    String bday = "";
	    String eday = "";
	    bday = StringUtil.isNullOrEmpty(request.getParameter("bday"))?bday:request.getParameter("bday");
	    eday = StringUtil.isNullOrEmpty(request.getParameter("eday"))?eday:request.getParameter("eday");
	    
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from orders t where t.order_date between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss') and order_id like '%"+orderId+"%' ";
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
		
	    String OrderSql= "select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME companyName,D.deptname,B.connector,B.connectorTel " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from orders EM where order_date between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss') and order_id like '%"+orderId+"%' order by "+orderStr+" asc) " +
	    	"A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
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
