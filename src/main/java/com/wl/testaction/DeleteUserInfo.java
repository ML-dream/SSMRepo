package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import cfmes.util.DealString;
import cfmes.util.sql_data;

public class DeleteUserInfo extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");
	    response.setHeader("Cache-Control","no-cache");
	    PrintWriter out = response.getWriter();
	    out.println("<?xml version='1.0' encoding='utf-8'?>");    
	    
	    String orderid = ds.toString(ds.toGBK(request.getParameter("orderid")));
	    String staffCode = request.getParameter("staffCode");
	    String userName =  URLDecoder.decode(request.getParameter("userName").trim(),"utf-8");
	    String password = request.getParameter("password");
	    String authority = request.getParameter("authority");

	    out.println("<delete>");
    	
    	String deletesql = "delete from sysusers where staff_code='"
    		+staffCode+"' and user_name='"
    		+userName+"' and password='"
    		+password+"' and authority='"+authority+"'";
    	System.out.println("deletesql=="+deletesql);
    	
		try{
	    	sqlbean.exeUpdateThrowExcep(deletesql);
	    	out.println("<isDeleted>1</isDeleted>");
		} catch(SQLException e){
			out.println("<isDeleted>0</isDeleted>");
			e.printStackTrace();
		} finally{
			sqlbean.closeConn();
		}
		out.println("</delete>");				
		out.flush();
		out.close();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
         doPost(request,response);
    }
}
