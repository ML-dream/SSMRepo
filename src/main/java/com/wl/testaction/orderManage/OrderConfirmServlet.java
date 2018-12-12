package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import cfmes.util.sql_data;

public class OrderConfirmServlet extends HttpServlet {

	private static final long serialVersionUID = 2861137531621601208L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println(this.getClass().getName());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String orderStatus = ChineseCode.toUTF8(request.getParameter("orderStatus").trim());
	    String confirmedAdvice = ChineseCode.toUTF8(request.getParameter("confirmedAdvice"));
	    String confirmAdvice = ChineseCode.toUTF8(request.getParameter("confirmAdvice"));
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		
//	    2016-11-24 xiexiaoming 审核通过加默认评价
	    int status = 0;
	    try {
			status = Integer.parseInt(orderStatus);
		} catch (Exception e) {
			// TODO: handle exception
		}
		switch (status) {
		case OrderStatus.NOTPASS:
			confirmAdvice += "不通过";
			break;
		case OrderStatus.PASS:
			confirmAdvice+= "通过";
			break;
		case OrderStatus.UPCLASSCHECK:
			confirmAdvice +="提交上级审核";
			break;
		default:
			break;
		}
//	    String sql = "update orders set confirmAdvice=confirmAdvice||'"+confirmAdvice+"("+changePerson+")',order_status='"+orderStatus+"'," +
//	    		"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
//	    		"where order_Id='"+orderId+"'";
	    
	    String sql = "update orders set confirmAdvice='"+confirmedAdvice+confirmAdvice+"("+changePerson+")',order_status='"+orderStatus+"'," +
		"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
		"where order_Id='"+orderId+"'";
	    
		System.out.println("sql="+sql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













