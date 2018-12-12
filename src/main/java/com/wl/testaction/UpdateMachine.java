package com.wl.testaction;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;

public class UpdateMachine extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("进入UpdateMachine函数体了已经！！！");
		
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    
	    
	    String machineid = request.getParameter("machineid").trim();
	    //String machinename = new String(request.getParameter("machinename").trim().getBytes("GBK"),"utf-8") ;
	    String machinename = URLDecoder.decode(request.getParameter("machinename").trim(),"utf-8");
	    String machinepower = request.getParameter("machinepower").trim();
	    String machineworker = new String(request.getParameter("machineworker").trim().getBytes("GBK"),"utf-8") ;

	    String updateMachineSql = "update machinfo set machinename='"+machinename
	    +"',power='"+machinepower+"',worker='"+machineworker+"' where machineid='"+machineid+"'";
	    System.out.println("userSql=="+updateMachineSql);

		try{
			Sqlhelper.executeUpdate(updateMachineSql, null);
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













