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

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.UUIDHexGenerator;

public class OrderPayedSpecServlet extends HttpServlet {

	private static final long serialVersionUID = -3704497957239069516L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
	    String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId").trim());
	    String payedTime = ChineseCode.toUTF8(request.getParameter("payedTime").trim());
	    String payedNum = ChineseCode.toUTF8(request.getParameter("payedNum").trim());
	    System.out.println(payedNum);
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String mainId = UUIDHexGenerator.getInstance().generate();
	    
		String sql = "insert into orderpayed(mainId,orderId,productId,FProductId,payedNum,payedTime,payedPerson)" +
						"values('"+mainId+"','"+orderId+"','"+productId+"','"+fproductId+"','"+payedNum+"'," +
						"to_date('"+payedTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"')";
		System.out.println(sql);
		
		String updateSql = "update order_detail set changetime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),alreadypaynum=(select sum(payednum) from orderpayed where orderId='"+orderId+"' and productId='"+productId+"' and fproductId='"+fproductId+"')" +
				"where order_id='"+orderId+"' and product_id='"+productId+"' and fproduct_id='"+fproductId+"'";
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			sqlData.exeUpdateThrowExcep(updateSql);
			
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


}
