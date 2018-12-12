package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import cfmes.util.DealString;
import cfmes.util.sql_data;

public class aoDispatch_Delete extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		
		request.setCharacterEncoding("utf-8"); 			   
	    String orderid = request.getParameter("orderid");
	    String itemid = request.getParameter("itemid");
	    String operid = request.getParameter("operid");
	
        response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");
		    
	    PrintWriter out = response.getWriter();

	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    out.println("<delete>");
    	
    	String deletesql = "delete from aodispatch where " +
    			"ORDER_ID='"+orderid+"' " +
    			"and PRODUCT_ID='"+itemid+"' " +
    			"and AO_NO='"+operid+"'";
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
