 package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckinDetl;
import com.wl.tools.Sqlhelper;

public class EditDetailSheetServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheet_id");
		String checkindetl_id=request.getParameter("checkindetl_id");
		String warehouse_id=request.getParameter("warehouse_id");
		String sql="select * from mycheckin_detl where checksheet_id='"+checksheet_id+"' and checkindetl_id='"+checkindetl_id+"'";
		System.out.println("sql="+sql);
		CheckinDetl checkindetl=new CheckinDetl();
		try{
			checkindetl=Sqlhelper.exeQueryBean(sql, null, CheckinDetl.class);
			request.setAttribute("checkindetl", checkindetl);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("warehouse_id", warehouse_id);
		request.getRequestDispatcher("Checkin/editDetailSheet.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
