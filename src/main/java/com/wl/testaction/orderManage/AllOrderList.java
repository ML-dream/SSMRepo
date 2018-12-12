package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class AllOrderList extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AllOrderList() {
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
//	加载所有订单
		System.out.println(this.getClass().getName());
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    
	    String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");
	    if(!para.isEmpty()){
	    	pageNo = Integer.parseInt(para) +1;
	    }
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String bday = "";
	    String eday = "";
	    bday = StringUtil.isNullOrEmpty(request.getParameter("bday"))?bday:request.getParameter("bday");
	    eday = StringUtil.isNullOrEmpty(request.getParameter("eday"))?eday:request.getParameter("eday");
	    String isExceed="1";
	    isExceed = StringUtil.isNullOrEmpty(request.getParameter("isExceed"))?isExceed:request.getParameter("isExceed");
	    
	    String creater = "";	//接收人
	    String status = "";  //订单状态
	    creater = StringUtil.isNullOrEmpty(request.getParameter("creater"))?creater:request.getParameter("creater");
	    status = StringUtil.isNullOrEmpty(request.getParameter("status"))?status:request.getParameter("status");
	    
//	    是否超期  1表示超期订单
	    String orderMode = "3";
	    orderMode = StringUtil.isNullOrEmpty(request.getParameter("orderMode"))?orderMode:request.getParameter("orderMode");
	    String customer ="";
	    customer = StringUtil.isNullOrEmpty(request.getParameter("customer"))?customer:request.getParameter("customer").trim();
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
//		return ;
		String orderStatus = OrderStatus.PASS+"";
		String conditon = " where t.order_date between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss') and to_number(t.order_status) >= "+orderStatus;
		
		
		if(bday.isEmpty()||eday.isEmpty()){
			conditon = " where 1=1 and to_number(t.order_status) >= "+orderStatus;
		}
		
	    if(!creater.isEmpty()){
	    	conditon += " and t.createperson ='"+creater+"' ";
	    }
	    if(!status.isEmpty()){
	    	conditon += " and to_number(t.order_status)="+status;
	    }
	    if(!customer.isEmpty()){
	    	conditon += " and t.customer='"+customer+"'";
	    }
	    if(orderMode.equals("1")){
	    	conditon +=" and nvl(g.checkoutdate,sysdate)>t.endtime and nvl(h.noProductNum,0)>0";
	    }
	    
	    String totalCountSql = "select count(*) from orders t " +
	    		" left join checkoutDate g on g.orderId=t.order_id " +
	    		" left join unpayedOrderId h on h.orderId=t.order_id " +
	    		""+conditon;
	    
	    String conditionB="";
//	    if(isExceed.equals("0")){
//	    	conditionB += " and isexceed < 0 ";
//	    	totalCountSql = "select count(*) from orders t "+conditon+" and nvl((t.rendtime-t.endtime),0)<0";
//	    }
		
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
//		订单总价视图
		String priceView ="create or replace view orderValue as " +
				"select t.order_id,t.customer,sum(a.unitprice*a.purduct_num) orderprice from orders t left join order_detail a on a.order_id = t.order_id" +
				" group by t.order_id,t.customer;";
		try {
			System.out.println(priceView);
			Sqlhelper.executeUpdate(priceView, null);
			System.out.println("ok  "+priceView);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    String OrderSql= "select b.ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,b.CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME companyName,D.deptname,B.connector,B.connectorTel " +
	    		",b.createperson createrId,e.staff_name createPerson,to_char(f.orderprice) orderPrice,b.exceedcn,to_char(isexceed) isexceed,checkoutdate deliverDay " +
	    	"from (select A.*,nvl((a.rendtime-a.endtime),0) isexceed,ROWNUM row_num from (select t.*,g.checkoutdate from orders t " +
	    	" left join checkoutDate g on g.orderId=t.order_id " +
    		" left join unpayedOrderId h on h.orderId=t.order_id " +
	    	""+conditon+" order by "+orderStr+" asc) " +
	    	"A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " + 
	    	"left join employee_info e on b.createperson=e.staff_code "+
	    	"left join orderValue f on f.order_id=b.order_id "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+conditionB+"  order by "+orderStr;
	    
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
//		这段时间的订单总价     where ROWNUM<="+(countPerPage*pageNo)+"
		String totalPrice = "0";
		String sqla= "select to_char(sum(f.orderprice)) orderPrice " +
			 	"from (select A.*,nvl((a.rendtime-a.endtime),0) isexceed,ROWNUM row_num from (select t.* from orders t " +
			 	" left join checkoutDate g on g.orderId=t.order_id " +
	    		" left join unpayedOrderId h on h.orderId=t.order_id " +
			 	""+conditon+" order by "+orderStr+" asc) " +
			 	"A  order by "+orderStr+") B " +
			 	"left join orderValue f on f.order_id=b.order_id "+
			 	"where 1=1 "+conditionB+" ";
		try {
			System.out.println(sqla);
			totalPrice = Sqlhelper.exeQueryString(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"orderPrice\":"+totalPrice+",\"data\":"+json+"}";
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
