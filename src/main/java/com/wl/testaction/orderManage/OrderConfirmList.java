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
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class OrderConfirmList extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderConfirmList() {
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
	// 	合同审核
		System.out.println(this.getClass().getName());
		int pageNo=0;
	    int countPerPage=0;
	    int totalCount = 0;
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String bday = "";
	    String eday = "";
	    bday = StringUtil.isNullOrEmpty(request.getParameter("bday"))?bday:request.getParameter("bday");
	    eday = StringUtil.isNullOrEmpty(request.getParameter("eday"))?eday:request.getParameter("eday");
	    
	    String creater = "";	//接收人
	    String status = "";  //订单状态
	    creater = StringUtil.isNullOrEmpty(request.getParameter("creater"))?creater:request.getParameter("creater");
	    status = StringUtil.isNullOrEmpty(request.getParameter("status"))?status:request.getParameter("status");
	    
	    
	    String customer ="";
	    customer = StringUtil.isNullOrEmpty(request.getParameter("customer"))?customer:request.getParameter("customer").trim();
	    
		String orderStatus = OrderStatus.NEWORDER+"";
		String conditon = " where to_number(t.order_status) = "+orderStatus;
		
		if(!status.isEmpty()){
	    	if(status.equals("5")){
	    		conditon = " where to_number(t.order_status)>="+status;
	    	}else{
	    		conditon = " where to_number(t.order_status)="+status;
	    	}
	    }
		if(!bday.isEmpty()&& !eday.isEmpty()){
			conditon += " and t.order_date between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss') ";
		}
		
	    if(!creater.isEmpty()){
	    	conditon += " and t.createperson ='"+creater+"' ";
	    }
	    
	    if(!customer.isEmpty()){
	    	conditon += " and t.customer='"+customer+"'";
	    }
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
//		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
//		String from = OrderStatus.NEWORDER+"";
//		String to = OrderStatus.NOTPASS +"";
		
	    String totalCountSql = "select count(*) from orders t "+conditon;
//	    String[] params1 = {staffCode};
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
		
	    String OrderSql= "select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME,D.deptname,B.connector,B.connectorTel " +
	    	"from (select A.*,ROWNUM row_num from (select t.* from orders t "+conditon+" order by "+orderStr+" desc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+" desc ) B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr+" desc ";
	    
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
