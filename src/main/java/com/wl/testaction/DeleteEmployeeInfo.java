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

public class DeleteEmployeeInfo extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("进入DeleteMachineInfo函数体了已经！！");
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");
	    response.setHeader("Cache-Control","no-cache");
	    PrintWriter out = response.getWriter();
	    out.println("<?xml version='1.0' encoding='utf-8'?>");    
	    
	    String EmployeeID = request.getParameter("EmployeeID");
	    String EmployeeName = request.getParameter("EmployeeName");
	    
	    out.println("<delete>");
    	String deleteEmployeeSql = "delete from employee_info where staff_code='"+EmployeeID+"'";
    	System.out.println("deleteEmployeeSql=="+deleteEmployeeSql);
    	
		try{
	    	sqlbean.exeUpdateThrowExcep(deleteEmployeeSql);
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
