package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

public class AoPlanServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    System.out.println("进入AoPlanServlet函数体了已经！！！");

	    String PLAN_ID     =new String(request.getParameter("PLAN_ID").trim().getBytes("ISO-8859-1"),"utf-8") ;
		String PLAN_TIME   =new String(request.getParameter("PLAN_TIME").trim().getBytes("ISO-8859-1"),"utf-8");
		String PLAN_PERSON =new String(request.getParameter("PLAN_PERSON").trim().getBytes("ISO-8859-1"),"utf-8") ;
		String ORDER_ID    =new String(request.getParameter("ORDER_ID").trim().getBytes("ISO-8859-1"),"utf-8");
		String PRODUCT_ID  =new String(request.getParameter("PRODUCT_ID").trim().getBytes("ISO-8859-1"),"utf-8") ;
		String NUM         =new String(request.getParameter("NUM").trim().getBytes("ISO-8859-1"),"utf-8");
		String START_TIME  =new String(request.getParameter("START_TIME").trim().getBytes("ISO-8859-1"),"utf-8") ;
		String END_TIME    =new String(request.getParameter("END_TIME").trim().getBytes("ISO-8859-1"),"utf-8");
		String QUALITY_ID  =new String(request.getParameter("QUALITY_ID").trim().getBytes("ISO-8859-1"),"utf-8") ;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINESE);
		
		try {
			System.out.println("开始时间："+(format.parse(START_TIME)).getTime());
			System.out.println("结束时间："+(format.parse(END_TIME)).getTime());
			if ((format.parse(START_TIME)).getTime()>=(format.parse(END_TIME)).getTime()) {
				System.out.println("结束时间在开始时间之前！！！！重新制定计划！！");
				response.sendRedirect("/work/partplan/aoplan.jsp");
			}else {
				String  addAOPlanSql = "insert into ao_plan (PLAN_ID,PLAN_TIME,PLAN_PERSON,ORDER_ID," +
				"PRODUCT_ID,NUM,START_TIME,END_TIME,QUALITY_ID) values " +
				"('"+PLAN_ID+"',to_date('"+PLAN_TIME+"','yyyy-mm-dd,hh24:mi:ss'),'"
				+PLAN_PERSON+"','"+ORDER_ID+"','"+PRODUCT_ID+"','"+NUM
				+"',to_date('"+START_TIME+"','yyyy-mm-dd,hh24:mi:ss')" +
				",to_date('"+END_TIME+"','yyyy-mm-dd,hh24:mi:ss'),'"+QUALITY_ID+"')";
				System.out.println("addAOPlanSql=="+addAOPlanSql);
				sql_data sqlData = new sql_data();
				try {
					sqlData.exeUpdateThrowExcep(addAOPlanSql);
					request.setAttribute("addOk", "装配计划编制成功！！");
				} catch (SQLException e) {
					// TODO: handle exception
					request.setAttribute("addOk", "装配计划编制失败！！");
					e.printStackTrace();
				}
		
		//		this.getServletConfig().getServletContext().getRequestDispatcher("/partplan/aoplan.jsp").forward(request, response);
				response.sendRedirect("/work/partplan/aoplan.jsp");
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("日期转化比较异常！！！");
			e1.printStackTrace();
		}
		
		
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
