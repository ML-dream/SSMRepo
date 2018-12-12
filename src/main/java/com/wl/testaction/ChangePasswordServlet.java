package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class ChangePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");

		String staffCode = request.getParameter("staffCode").trim();
		String oldPassword = request.getParameter("oldPassword").trim();
		String newPassword = request.getParameter("newPassword").trim();
		String newPassword2 = request.getParameter("newPassword2").trim();
		
		System.out.println("staffCode=="+staffCode);
		System.out.println("oldPassword=="+oldPassword);
		System.out.println("newPassword=="+newPassword);
		System.out.println("newPassword2=="+newPassword2);
		
		HttpSession session = request.getSession();
		
		if ((((User)session.getAttribute("user")).getPassword()).equals(oldPassword)) {  //老密码验证成功
			
			if (newPassword.equals(newPassword2)) {
				System.out.println("两个新密码相同！！");
				sql_data sqlData = new sql_data();
				//下面开始处理重置密码操作
				String  newPasswordSql = "update sysusers set password='"+newPassword+"' where staff_code='"+staffCode+"'";
				System.out.println("newPasswordSql=="+newPasswordSql);
				
				try {
					sqlData.exeUpdateThrowExcep(newPasswordSql);
					
					request.setAttribute("changeOk", "密码更新成功！！");
				} catch (SQLException e) {
					// TODO: handle exception
					request.setAttribute("changeOk", "密码重置失败！！");
					e.printStackTrace();
				}
				
			}else {
				System.out.println("两个新密码不相同！！");
				request.setAttribute("changeOk", "您两次输入的新密码不相同！！");
			}
			
			
		}else {             //老密码验证失败
			request.setAttribute("changeOk", "您输入的密码不正确！！");
		}
		this.getServletConfig().getServletContext().getRequestDispatcher("/AddUser.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
