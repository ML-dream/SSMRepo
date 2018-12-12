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

public class DeleteAOInfo extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("进入DeleteAOInfo函数体了已经！！");
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");
	    response.setHeader("Cache-Control","no-cache");
	    PrintWriter out = response.getWriter();
	    out.println("<?xml version='1.0' encoding='utf-8'?>");    
	    
	    String PRODUCTID=URLDecoder.decode(request.getParameter("PRODUCTID").trim(),"utf-8");
	    String AO_NO=URLDecoder.decode(request.getParameter("AO_NO").trim(),"utf-8");
	    String AO_ORDER=URLDecoder.decode(request.getParameter("AO_ORDER").trim(),"utf-8");
	    
	    out.println("<delete>");
    	String deleteAOSql = "delete from employee_info where PRODUCTID='"+PRODUCTID+
    		"' and AO_NO='"+AO_NO+"' and AO_ORDER='"+AO_ORDER+"'";
    	System.out.println("deleteAOSql=="+deleteAOSql);
    	
		try{
	    	sqlbean.exeUpdateThrowExcep(deleteAOSql);
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
