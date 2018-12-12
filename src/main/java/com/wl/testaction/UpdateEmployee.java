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

public class UpdateEmployee extends HttpServlet {

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
	    
	    
	    String EmployeeID = request.getParameter("EmployeeID").trim();
	    String EmployeeName = URLDecoder.decode(request.getParameter("EmployeeName").trim(), "utf-8");
	    String deptid = URLDecoder.decode(request.getParameter("deptid").trim(), "utf-8");
	    String gender = URLDecoder.decode(request.getParameter("gender").trim(), "utf-8");
	    String schoolFrom = URLDecoder.decode(request.getParameter("schoolFrom").trim(), "utf-8");
	    String ADDRESS = URLDecoder.decode(request.getParameter("ADDRESS").trim(), "utf-8");
	    String techGrade = URLDecoder.decode(request.getParameter("techGrade").trim(), "utf-8");
	    String BIRTHDAY = request.getParameter("BIRTHDAY").trim();
	    String EDUCATION_LEVEL = URLDecoder.decode(request.getParameter("EDUCATION_LEVEL").trim(), "utf-8");
	    String SPECIALITY = URLDecoder.decode(request.getParameter("SPECIALITY").trim(), "utf-8");
	    String WORK_TYPE = URLDecoder.decode(request.getParameter("WORK_TYPE").trim(), "utf-8");
	    String OFFICE_PHNE = new String(request.getParameter("OFFICE_PHNE").trim().getBytes("GBK"),"utf-8") ;
	    String MOBILE_PHONE = new String(request.getParameter("MOBILE_PHONE").trim().getBytes("GBK"),"utf-8") ;
	    String HOME_PHONE = new String(request.getParameter("HOME_PHONE").trim().getBytes("GBK"),"utf-8") ;
	    String POSITION = URLDecoder.decode(request.getParameter("POSITION").trim(), "utf-8");
	    String WORKTIME = request.getParameter("WORKTIME").trim();
	    String QQ = new String(request.getParameter("QQ").trim().getBytes("GBK"),"utf-8");
	    String EMAIL = new String(request.getParameter("EMAIL").trim().getBytes("GBK"),"utf-8") ;
	    String RFID_CODE = URLDecoder.decode(request.getParameter("RFID_CODE").trim(), "utf-8");
	    
	    String updateEmployeeSql = "update employee_info set staff_name='"+EmployeeName+"', DEPT_ID='"+
	    deptid+"',GENDER='"+gender+"',EDUCATION_LEVEL='"
	    +EDUCATION_LEVEL+"',SCHOOL_FROM='"+schoolFrom+"',SPECIALITY='"+SPECIALITY+"',WORK_TYPE='"
	    +WORK_TYPE+"',TECHNICAL_GRADE='"+techGrade+"',ADDRESS='"+ADDRESS+"',OFFICE_PHNE='"
	    +OFFICE_PHNE+"',MOBILE_PHONE='"+MOBILE_PHONE+"',HOME_PHONE='"+HOME_PHONE+"',POSITION='"
	    +POSITION+"',WORKTIME='"+WORKTIME+"',QQ='"+QQ+"',EMAIL='"+EMAIL+"',RFID_CODE='"+RFID_CODE+"' where staff_code='"+EmployeeID+"'";
	    System.out.println("updateEmployeeSql=="+updateEmployeeSql);

		try{
			Sqlhelper.executeUpdate(updateEmployeeSql, null);
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













