package com.wl.testaction.outAssistManage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;
/*import oracle.net.aso.l;*/

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EditOutAssistSpecServlet extends HttpServlet {

	private static final long serialVersionUID = -2125593510580536066L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String orderId= ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String deptId= ChineseCode.toUTF8(request.getParameter("deptId"));
		String companyId= ChineseCode.toUTF8(request.getParameter("companyId").trim());
		String companyName= ChineseCode.toUTF8(request.getParameter("companyName").trim());
		System.out.println(companyName);
		String principal= ChineseCode.toUTF8(request.getParameter("principal").trim());
		
		String connectorName= ChineseCode.toUTF8(request.getParameter("connectorName").trim());
		String connectorTel= ChineseCode.toUTF8(request.getParameter("connectorTel"));
		String startDate= ChineseCode.toUTF8(request.getParameter("startDate").trim());
		String planEndDate= ChineseCode.toUTF8(request.getParameter("planEndDate"));
		String trueEndDate= ChineseCode.toUTF8(request.getParameter("trueEndDate"));
		
		String fine= ChineseCode.toUTF8(request.getParameter("fine"));
		String shouldPay= ChineseCode.toUTF8(request.getParameter("shouldPay"));
		String alreadyPay= ChineseCode.toUTF8(request.getParameter("alreadyPay"));
		String notPay= ChineseCode.toUTF8(request.getParameter("notPay"));
		String isBusy= ChineseCode.toUTF8(request.getParameter("isBusy").trim());
		
	    String memo = ChineseCode.toUTF8(request.getParameter("memo"));
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String sql = "update outAssist set " +
	    		"deptId='"+deptId+"',companyId='"+companyId+"',companyName='"+companyName+"',principal='"+principal+"'," +
	    		"connectorName='"+connectorName+"',connectorTel='"+connectorTel+"'," +
	    		"startDate=to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss'),planEndDate=to_date('"+planEndDate+"','yyyy-mm-dd,hh24:mi:ss'),trueEndDate=to_date('"+trueEndDate+"','yyyy-mm-dd,hh24:mi:ss')," +
	    		"fine='"+fine+"',shouldPay='"+shouldPay+"',alreadyPay='"+alreadyPay+"',notPay='"+notPay+"',isBusy='"+isBusy+"'," +
	    		"memo='"+memo+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),changePerson='"+changePerson+"' " +
	    		"where orderId='"+orderId+"'";
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













