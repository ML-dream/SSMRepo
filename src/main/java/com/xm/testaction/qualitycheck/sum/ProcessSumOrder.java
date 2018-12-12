package com.xm.testaction.qualitycheck.sum;

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

import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ProcessSumOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProcessSumOrder() {
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
// 生产进度查询，点击订单树订单层
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "PRODUCT_ID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    
	    HttpSession session = request.getSession();
		String staffcode = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String totalCountSql = "select count(*) from order_detail where order_id=? ";
	    String[] params1 = {orderId};
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    String OrderSql= "select B.ORDER_ID,PRODUCT_ID,ISSUE_NUM issueNum," +
	    		"PURDUCT_NUM productnum," +
	    		"f.statusname productStatusName,FPRODUCT_ID," +
	    		"FINISHNUM," +
	    		"PRODUCT_NAME productName," +
	    		"D.customer,D.connector,C.companyName,E.deptname " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from order_detail EM where EM.order_id=? and cengci='2'  order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join orders D on D.order_id=B.order_id " +
	    	"left join customer C on D.CUSTOMER=C.COMPANYID " +
	    	"left join dept E on E.DEPTID=D.DEPT_USER " +
	    	"left join PRODUCTSTATUS f on b.product_status = f.statusid "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    String[] params2 = {orderId};
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, params2, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
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
