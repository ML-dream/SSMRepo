package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class GoCheckOrderDetailListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String para= "orderList";
		try {
			para = request.getParameter("para");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String result = "";
//		para 判定是哪个页面跳转  seeAll，orderList
		if(para.equals("orderList")){
			result = "orderManage/CheckOrderDetailList.jsp";	//跳转目标
			request.setAttribute("orderId", orderId);
		}else if(para.equals("seeAll")){
			String bday = request.getParameter("bday");
			String eday = request.getParameter("eday");
			String page = request.getParameter("page");
			String rowIndex = request.getParameter("rowIndex");
			
			String status = request.getParameter("status");
			String creater = request.getParameter("creater");
			String fcustomer = request.getParameter("customer");
			
			result = "orderManage/seeAllProduct.jsp";
			
			String	orderSql = 
		    	"select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME endTime,CUSTOMER,ORDER_STATUS orderStatus,B.companyName,A.connector,A.connectortel,A.orderPaper,a.createperson " +
		    	"from orders A left join customer B on B.companyId=A.customer " +
		    	"where ORDER_ID=? ";
			String[] params = {orderId};
			Order order = new Order();
			try {
				order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			order.setEday(eday);
			order.setBday(bday);
			order.setRowIndex(rowIndex);
			order.setPage(page);
			
			order.setStatus(status);
			order.setCreater(creater);
			order.setFcustomer(fcustomer);
			
			request.setAttribute("order", order);
		}
		
		
	    request.getRequestDispatcher(result).forward(request, response);
	}


}
