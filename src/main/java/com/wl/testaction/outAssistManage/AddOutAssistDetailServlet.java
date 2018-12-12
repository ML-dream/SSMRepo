package com.wl.testaction.outAssistManage;

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

import com.wl.forms.User;
import com.wl.tools.ChineseCode;

public class AddOutAssistDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -8411056829967600593L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId= ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String itemId= ChineseCode.toUTF8(request.getParameter("itemId"));
		String itemName= ChineseCode.toUTF8(request.getParameter("itemName").trim());
		String drawingId= ChineseCode.toUTF8(request.getParameter("drawingId").trim());
		String num= ChineseCode.toUTF8(request.getParameter("num").trim());
		
		String numUnit= ChineseCode.toUTF8(request.getParameter("numUnit").trim());
		String startDate= ChineseCode.toUTF8(request.getParameter("startDate").trim());
		String planEndDate= ChineseCode.toUTF8(request.getParameter("planEndDate").trim());
		String endDate= ChineseCode.toUTF8(request.getParameter("endDate"));
		String unitPrice= ChineseCode.toUTF8(request.getParameter("unitPrice"));
		
		String totalPrice= ChineseCode.toUTF8(request.getParameter("totalPrice"));
		String detail= ChineseCode.toUTF8(request.getParameter("detail"));
	    String memo = ChineseCode.toUTF8(request.getParameter("memo"));
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String sql = "insert into outAssistDetail" +
	    		"(orderId,itemId,itemName,drawingId,num," +
	    		"numUnit,unitPrice,totalPrice,detail,startDate," +
	    		"planEndDate,endDate,memo," +
	    		"createTime,changeTime,createPerson,changePerson)" +
	    		"values" +
	    		"('"+orderId+"','"+itemId+"','"+itemName+"','"+drawingId+"','"+num+"'," +
	    		"'"+numUnit+"','"+unitPrice+"','"+totalPrice+"','"+detail+"',to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss'),"  +
	    		"to_date('"+planEndDate+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+memo+"'," +
	    		"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"')";
	    System.out.println("sql="+sql);

		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}


}
