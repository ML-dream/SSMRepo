package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.DateTimeUtil;
import com.wl.tools.Sqlhelper;

public class AddGT extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    
	    System.out.println("进入AddGT函数体了已经！！！");

		//url: "AddGT?taskID="+taskID+"&taskName="+encodeURI(encodeURI(taskName))+"&percentComplete="+percentComplete+"&taskStart="+taskStart+"&taskFinish="+taskFinish,
        
	    String taskID = request.getParameter("taskID").trim();
	    String taskName = URLDecoder.decode(request.getParameter("taskName").trim(),"utf-8");
	    String percentComplete = request.getParameter("percentComplete").trim();
	    String taskStart = URLDecoder.decode(request.getParameter("taskStart").trim(),"utf-8");
	    String taskFinish = URLDecoder.decode(request.getParameter("taskFinish").trim(),"utf-8");
	    String itemId = URLDecoder.decode(request.getParameter("itemId").trim(),"utf-8");
	    String machineId = URLDecoder.decode(request.getParameter("machineId").trim(),"utf-8");
	    
	    System.out.println(taskID+" "+taskName+" "+taskStart+" "+taskFinish+ " "+ percentComplete+" "+itemId+"  "+machineId);
	    
	    String updateSql = "update process_plan set start_time=to_date('"+taskStart+"','yyyy-mm-dd,hh24:mi:ss') " +
	    					",end_time=to_date('"+taskFinish+"','yyyy-mm-dd,hh24:mi:ss')  " +
	    					"where item_id='"+itemId+"' and oper_id='"+taskID+"'";
	    System.out.println(updateSql);
	    
	    String updateNameSql = "update fo_detail set fo_opname='"+taskName+"' " +
	    						"where item_id='"+itemId+"' and FO_OPERID='"+taskID+"'";
	    System.out.println(updateNameSql);
	    try {
			Sqlhelper.executeUpdate(updateSql, null);
			Sqlhelper.executeUpdate(updateNameSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//this.getServletConfig().getServletContext().getRequestDispatcher("/AOmanage.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}

