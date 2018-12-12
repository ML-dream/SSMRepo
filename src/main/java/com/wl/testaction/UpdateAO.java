package com.wl.testaction;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.activation.Server;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;

public class UpdateAO extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("进入UpdateMachine函数体了已经！！！");
		request.setCharacterEncoding("utf-8");
		
		
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    
	    
	    String PRODUCTID = URLDecoder.decode(request.getParameter("PRODUCTID").trim(), "utf-8");
	    String AO_NO = URLDecoder.decode(request.getParameter("AO_NO").trim(), "utf-8");
	    String ISSUE_NUM = URLDecoder.decode(request.getParameter("ISSUE_NUM").trim(), "utf-8");
	    String AO_ORDER = URLDecoder.decode(request.getParameter("AO_ORDER").trim(), "utf-8");
	    String ITEMID = URLDecoder.decode(request.getParameter("ITEMID").trim(), "utf-8");
	    String AOVER = URLDecoder.decode(request.getParameter("AOVER").trim(), "utf-8");
	    String PARENTAO_NO = URLDecoder.decode(request.getParameter("PARENTAO_NO").trim(), "utf-8");
	    String AO_TIME = URLDecoder.decode(request.getParameter("AO_TIME").trim(), "utf-8");
	    String AONAME = URLDecoder.decode(request.getParameter("AONAME").trim(), "utf-8");
	    String WORKPLACECODE = URLDecoder.decode(request.getParameter("WORKPLACECODE").trim(), "utf-8");
	    String WORKPLACENAME = URLDecoder.decode(request.getParameter("WORKPLACENAME").trim(), "utf-8");
	    String AO_CONTENT = URLDecoder.decode(request.getParameter("AO_CONTENT").trim(), "utf-8");
	    
	    String updateAOSql = "update aodetail set ISSUE_NUM='"+ISSUE_NUM+
	    		"', ITEMID='"+ITEMID+"', AOVER='"+AOVER+"', PARENTAO_NO='"+PARENTAO_NO+
	    		"', AO_TIME='"+AO_TIME+"', AONAME='"+AONAME+"', WORKPLACECODE='"+WORKPLACECODE+
	    		"',WORKPLACENAME='"+WORKPLACENAME+"',AO_CONTENT='"+AO_CONTENT+"' where PRODUCTID='"+PRODUCTID+"' and AO_NO='"+AO_NO+"' and AO_ORDER='"+AO_ORDER+"'";
	   
	    System.out.println("updateAOSql=="+updateAOSql);

		try{
			Sqlhelper.executeUpdate(updateAOSql, null);
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













