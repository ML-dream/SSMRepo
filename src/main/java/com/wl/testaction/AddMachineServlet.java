package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class AddMachineServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");

	    String machineID = request.getParameter("machineID").trim();
		//String machineName = URLDecoder.decode(request.getParameter("machineName").trim(), "utf-8");
	    String machineName = new String(request.getParameter("machineName").trim().getBytes("ISO-8859-1"),"utf-8") ;
		System.out.println("machineName="+machineName);
	    String machinePower = request.getParameter("machinePower").trim();
		String machinespec = request.getParameter("machinespec").trim();
		
		
		//String processplan_a=new String(request.getParameter("processplan_a").getBytes("ISO-8859-1"),"utf-8");
		HttpSession session = request.getSession();
		
	
		String  addMachineSql = 
			"insert into machinfo (machineid,machinename,power,machinespec) values ('"
			+machineID+"','"+machineName+"','"+machinePower+"','"+machinespec+"')";
		System.out.println("addMachineSql=="+addMachineSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(addMachineSql);
			request.setAttribute("addOk", "机床添加成功！！");
		} catch (SQLException e) {
			// TODO: handle exception
			request.setAttribute("addOk", "机床添加失败！！");
			e.printStackTrace();
		}
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/machineManage.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
