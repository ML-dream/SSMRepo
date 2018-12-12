package com.wl.testaction.orderManage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Customer;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class OrderAllDetailListServlet extends HttpServlet {

	private static final long serialVersionUID = 3469192252231863249L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "PRODUCT_ID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
//	    判断发起页面 xm
	    String para = "";
	    try {
			para =StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para");
		} catch (Exception e) {
			// TODO: handle exception
		}
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
//		String staffcode = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String totalCountSql = "select count(*) from order_detail where order_id=? ";
	    String[] params1 = {orderId};
	    
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    String OrderSql= "select B.ORDER_ID,PRODUCT_ID,ISSUE_NUM,B.LOT,SORTIE," +
	    		"PURDUCT_NUM productnum,B_TIME,E_TIME,SB_TIME,SE_TIME," +
	    		"DEPT_ID,PRODUCT_STATUS productStatus,FPRODUCT_ID," +
	    		"MOVENUM,UP_LOT,FINISHNUM,MEMO,PLANCHANNO," +
	    		"PRODUCT_NAME,SPEC,MADEPLACE,drawingId,unitprice," +
	    		"planProfit,customerkoukuan,shijixiaoshouer,yijinghuikuan,yukuan," +
	    		"isduizhanghan,islailiao,isjiaohuo,D.customer,D.connector,C.companyName,E.deptname,B.issue_num,B.quotationtotal " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from order_detail EM where EM.order_id=? and cengci='2'  order by "+orderStr+" asc) A  order by "+orderStr+") B " +
	    	"left join orders D on D.order_id=B.order_id " +
	    	"left join customer C on D.CUSTOMER=C.COMPANYID " +
	    	"left join dept E on E.DEPTID=D.DEPT_USER " +
	    	" order by "+orderStr;
	    String[] params2 = {orderId};
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, params2, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(orderList);
	    
		if(para.equals("seeAll")){
			String orderSum = null;	//订单总价
			String viewsql = "create or replace view ordersum as " +
					"select t.order_id,sum(a.unitprice*a.purduct_num) orderprice from orders t left join order_detail a on a.order_id = t.order_id group by t.order_id";
			try {
				System.out.println(viewsql);
				Sqlhelper.executeUpdate(viewsql, null);
				System.out.println("ok  "+viewsql);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String sumSql="select t.orderprice from ordersum t where t.order_id='"+orderId+"'";
			try {
				System.out.println(sumSql);
				orderSum =Sqlhelper.exeQueryString(sumSql, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			json = "{\"sum\":\""+orderSum+"\",\"data\":"+json+"}";
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













