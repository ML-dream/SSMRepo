package com.wl.testaction;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;

public class UpdateUser extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    String staffCode = request.getParameter("staffCode").trim();
	    String userName = URLDecoder.decode(request.getParameter("userName").trim(),"utf-8");
	    //String userName = ChineseCode.toUTF8(request.getParameter("userName").trim());		//这个是针对form表单提交，从而处理字符集的方法
	    String password = request.getParameter("password").trim();
	    String authority = request.getParameter("authority").trim();
	    
	    System.out.println("authority=="+authority);
	    System.out.println("staffCode=="+staffCode);
	    System.out.println("userName=="+userName);
	    System.out.println("password=="+password);
	    

	    String userSql = "update sysusers set user_name='"+userName+"',password='"+password+"',authority='"+authority+"' where staff_code='"+staffCode+"'";
	    System.out.println("userSql=="+userSql);

		try{
			Sqlhelper.executeUpdate(userSql, null);
			out.println("<ok>1</ok>");
		}catch(Exception e){
			out.println("<ok>0</ok>");
			System.out.println("增加用户时出错！错误为："+e);
		}
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













