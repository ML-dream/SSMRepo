package com.wl.testaction.sysManage;

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
import com.wl.tools.MD5;
import com.wl.tools.UUIDHexGenerator;

public class AddSysUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 2905607497150346361L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String userId= ChineseCode.toUTF8(request.getParameter("userId").trim());
		String staffCode= ChineseCode.toUTF8(request.getParameter("staffCode"));
		String userName= ChineseCode.toUTF8(request.getParameter("userName").trim());
		String password= ChineseCode.toUTF8(request.getParameter("password").trim());
		password = MD5.MD5Convert(password);
		String authority= ChineseCode.toUTF8(request.getParameter("authority").trim());
		
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String UUID = UUIDHexGenerator.getInstance().generate();
	    
	    String sql = "insert into sysUsers(user_Id,staff_Code,user_Name,password,authority," +
	    		"OLD_PASSWORD,REGISTER_TIME,PWD_MODIFY_TIME,"+
	    		"createPerson,createTime,changePerson,changeTime)" +
	    		"values('"+userId+"','"+staffCode+"','"+userName+"','"+password+"','"+authority+"'," +
	    		"'"+password+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
	    		"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
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
