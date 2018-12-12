package com.wl.testaction.deptManage;

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
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class AddDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String deptId= ChineseCode.toUTF8(request.getParameter("deptId").trim());
		String headStaffId= request.getParameter("headStaffId");
		request.getParameter("FDeptId").trim();
		String FDeptId= StringUtil.isNullOrEmpty(request.getParameter("FDeptId"))?"0":request.getParameter("FDeptId").trim();
		String deptLevel= request.getParameter("deptLevel").trim();
		String deptName= request.getParameter("deptName").trim();
		String isKey= request.getParameter("isKey").trim();
		
	    String memo = request.getParameter("memo");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String sql = "insert into dept(deptId,headStaffId,FDeptId,deptLevel,deptName," +
	    		"isKey,memo,createPerson,createTime,changePerson,changeTime)" +
	    		"values('"+deptId+"','"+headStaffId+"','"+FDeptId+"','"+deptLevel+"','"+deptName+"'," +
	    		"'"+isKey+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
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
