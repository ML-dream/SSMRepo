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

public class AddOutAssistServlet extends HttpServlet {

	private static final long serialVersionUID = -8411056829967600593L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
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
	    
	    String sql = "insert into outAssist" +
	    		"(orderId,deptId,companyId,companyName,principal," +
	    		"connectorName,connectorTel,startDate,planEndDate,trueEndDate," +
	    		"fine,shouldPay,alreadyPay,notPay,isBusy," +
	    		"memo,createTime,changeTime,createPerson,changePerson)" +
	    		"values" +
	    		"('"+orderId+"','"+deptId+"','"+companyId+"','"+companyName+"','"+principal+"'," +
	    		"'"+connectorName+"','"+connectorTel+"',to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss')," +
	    		"to_date('"+planEndDate+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+trueEndDate+"','yyyy-mm-dd,hh24:mi:ss')," +
	    		"'"+fine+"','"+shouldPay+"','"+alreadyPay+"','"+notPay+"','"+isBusy+"'," +
	    		"'"+memo+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"')";
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
