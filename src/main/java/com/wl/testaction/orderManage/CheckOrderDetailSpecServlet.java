package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckOrderDetailSpecServlet extends HttpServlet {
	private static final long serialVersionUID = 5960822724073807776L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String orderId = request.getParameter("orderId");
	    String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
//	    checkOrder
		String	orderSql = 
			"select B.ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,B.LOT lot,SORTIE," +
    		"PURDUCT_NUM productNum,B_TIME bTime,E_TIME eTime,SB_TIME sbTime,SE_TIME seTime," +
    		"DEPT_ID deptId,PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId," +
    		"MOVENUM,UP_LOT upLot,FINISHNUM,MEMO,PLANCHANNO," +
    		"PRODUCT_NAME productName,SPEC,MADEPLACE,drawingId,unitprice," +
    		"planProfit,customerkoukuan,shijixiaoshouer,yijinghuikuan,yukuan," +
    		"isduizhanghan,islailiao,isjiaohuo,D.customer ,D.connector,C.companyName,E.deptname " +
	    	"from  order_detail B " +
	    	"left join orders D on D.order_id=B.order_id " +
	    	"left join customer C on D.CUSTOMER=C.COMPANYID " +
	    	"left join dept E on E.DEPTID=D.DEPT_USER " +
		    "where B.ORDER_ID=? and B.product_Id=? ";
		
		String[] params = {orderId,productId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("order", order);
		
//		request.getRequestDispatcher("orderManage/CheckOrderDetailSpec.jsp").forward(request, response);
		request.getRequestDispatcher("orderManage/productForConfirm.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













