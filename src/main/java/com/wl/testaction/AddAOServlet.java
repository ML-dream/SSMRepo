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

public class AddAOServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    
	    //System.out.println(request.getPathInfo());
	    System.out.println("进入AddAOServlet函数体了已经！！！");

		String PRODUCTID =new String(request.getParameter("PRODUCTID").trim().getBytes("ISO-8859-1"),"utf-8") ;
		String AO_NO =new String(request.getParameter("AO_NO").trim().getBytes("ISO-8859-1"),"utf-8");
		String ISSUE_NUM =new String(request.getParameter("ISSUE_NUM").trim().getBytes("ISO-8859-1"),"utf-8");
		String AO_ORDER =new String(request.getParameter("AO_ORDER").trim().getBytes("ISO-8859-1"),"utf-8");
		String ITEMID =new String(request.getParameter("ITEMID").trim().getBytes("ISO-8859-1"),"utf-8");
		String AOVER =new String(request.getParameter("AOVER").trim().getBytes("ISO-8859-1"),"utf-8");
		String PARENTAO_NO =new String(request.getParameter("PARENTAO_NO").trim().getBytes("ISO-8859-1"),"utf-8");
		String AO_TIME =new String(request.getParameter("AO_TIME").trim().getBytes("ISO-8859-1"),"utf-8");
		String AONAME =new String(request.getParameter("AONAME").trim().getBytes("ISO-8859-1"),"utf-8");
		String WORKPLACECODE =new String(request.getParameter("WORKPLACECODE").trim().getBytes("ISO-8859-1"),"utf-8");
		String WORKPLACENAME =new String(request.getParameter("WORKPLACENAME").trim().getBytes("ISO-8859-1"),"utf-8");
		String AO_CONTENT =new String(request.getParameter("AO_CONTENT").trim().getBytes("ISO-8859-1"),"utf-8");

		HttpSession session = request.getSession();

		String  addAOSql = "insert into aodetail (PRODUCTID,AO_NO,ISSUE_NUM,AO_ORDER," +
				"ITEMID,AOVER,PARENTAO_NO,AO_TIME,AONAME,WORKPLACECODE,WORKPLACENAME,AO_CONTENT) values " +
				"('"+PRODUCTID+"','"+AO_NO+"','"+ISSUE_NUM+"','"+AO_ORDER+"','"+ITEMID+"','"
				+AOVER+"','"+PARENTAO_NO+"','"+AO_TIME+"','"+AONAME+"','"+WORKPLACECODE+"','"
				+WORKPLACENAME+"','"+AO_CONTENT+"')";
		System.out.println("addAOSql=="+addAOSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(addAOSql);
			request.setAttribute("addOk", "装配信息添加成功！！");
		} catch (SQLException e) {
			// TODO: handle exception
			request.setAttribute("addOk", "装配信息添加失败！！");
			e.printStackTrace();
		}
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/AOmanage.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
