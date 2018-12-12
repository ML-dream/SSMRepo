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

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import cfmes.util.sql_data;

public class CustomerItemSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 5303517042445937407L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String customeId = ChineseCode.toUTF8(request.getParameter("customeId").trim());
	    String customeName = ChineseCode.toUTF8(request.getParameter("customeName").trim());
	    String itemId = ChineseCode.toUTF8(request.getParameter("itemId").trim());
	    String itemname = ChineseCode.toUTF8(request.getParameter("itemname").trim());
	    
	    String itemType = ChineseCode.toUTF8(request.getParameter("itemType").trim());
	    String itemspec = ChineseCode.toUTF8(request.getParameter("itemspec").trim());
	    String itemNum = ChineseCode.toUTF8(request.getParameter("itemNum").trim());
	    String itemPrice = ChineseCode.toUTF8(request.getParameter("itemPrice").trim());
	    String unit = ChineseCode.toUTF8(request.getParameter("unit").trim());
	    
	    String starttime = ChineseCode.toUTF8(request.getParameter("starttime").trim());
	    String endtime = ChineseCode.toUTF8(request.getParameter("endtime").trim());
	    String person = ChineseCode.toUTF8(request.getParameter("person").trim());
	    String isCheckIn = ChineseCode.toUTF8(request.getParameter("isCheckIn").trim());
	    String memo = ChineseCode.toUTF8(request.getParameter("memo"));
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		
	    String sql = "update customeasset set " +
	    	"itemname='"+itemname+"',itemspec='"+itemspec+"',starttime=to_date('"+starttime+"','yyyy-mm-dd,hh24:mi:ss'),endtime=to_date('"+endtime+"','yyyy-mm-dd,hh24:mi:ss')," +
			"person='"+person+"',memo='"+memo+"',itemType='"+itemType+"',itemNum='"+itemNum+"'," +
			"itemPrice='"+itemPrice+"',isCheckIn='"+isCheckIn+"',unit='"+unit+"',customeName='"+customeName+"'," +
			"changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),changePerson='"+changePerson+"' " +
			"where orderId='"+orderId+"' and customeId='"+customeId+"' and itemId='"+itemId+"'";
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













