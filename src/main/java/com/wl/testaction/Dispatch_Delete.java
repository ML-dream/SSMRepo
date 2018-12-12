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

public class Dispatch_Delete extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		
		request.setCharacterEncoding("utf-8"); 			   
	    String orderid = ds.toString(ds.toGBK(request.getParameter("orderid")));
	    String issuenum = ds.toString(ds.toGBK(request.getParameter("issuenum")));
	    String itemid = ds.toString(ds.toGBK(request.getParameter("itemid")));
	    String operid = ds.toString(ds.toGBK(request.getParameter("operid")));
	
        response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");
		    
	    PrintWriter out = response.getWriter();

	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    out.println("<delete>");
    	
    	String deletesql = "delete from departuresheet where " +
    			"orderid='"+orderid+"' " +
    			"and issue_num='"+issuenum+"' " +
    			"and item_id='"+itemid+"' " +
    			"and oper_id='"+operid+"'";
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
