package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;

public class AddUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");

		String staffCode = request.getParameter("staffCode").trim();
		String userName = ChineseCode.toUTF8(request.getParameter("userName").trim());
		String password = request.getParameter("password").trim();
		String authority = request.getParameter("authority").trim();

		try{
			//增加用户
			String addUserSql = 
				"insert into sysusers (user_id,staff_code,user_name,password,authority) " +
				"values('"+staffCode+"','"+staffCode+"','"+userName+"','"+password+"','"+authority+"')";
			System.out.println("addUserSql==="+addUserSql);
			
			Sqlhelper.executeUpdate(addUserSql, null);
			out.println("<user>");
			out.println("<staffCode>"+staffCode+"</staffCode>");
			out.println("<userName>"+userName+"</userName>");
			out.println("<password>"+password+"</password>");
			out.println("<authority>"+authority+"</authority>");
			out.println("</user>");
			response.sendRedirect("AddUser.jsp");
		}catch(Exception e){
			System.out.println("增加用户时出错！错误为："+e);
			System.out.println("用户已存在！！！");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}

