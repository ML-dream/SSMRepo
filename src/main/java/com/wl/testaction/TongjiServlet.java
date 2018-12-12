package com.wl.testaction;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class TongjiServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setHeader("Charset","utf-8");    
		
		String itemId = request.getParameter("itemId");
		String processId = request.getParameter("processId");
		
		double part_finish_rate = 0;
		double part_pass_rate   = 0;
		double finish_rate      = 0;
		double pass_rate        = 0;
		
		System.out.println(itemId + "  " + processId);
		ResultSet rs = null;
		
		
		//这是零件，而不是工序
		String partSql="select part_num,finish_num,pass_num,failure_num from work.part_temp where item_id='"+itemId+"'";
		System.out.println(partSql);
		
		try {
			rs = Sqlhelper.executeQuery(partSql, null);
			if (rs.next()) {
				int part_num         = rs.getInt("part_num");
				int part_finish_num  = rs.getInt("finish_num");
				int part_pass_num    = rs.getInt("pass_num");
				int part_failure_num = rs.getInt("failure_num");
				System.out.println(part_finish_num + " " +part_pass_num + " " + part_failure_num);
				if(part_num!=0){
					part_finish_rate =((double)part_finish_num/part_num)*100;
					part_pass_rate   =((double)part_pass_num/part_num)*100;
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("part_finish_rate", part_finish_rate);
		request.setAttribute("part_pass_rate", part_pass_rate);
		request.setAttribute("finish_rate", finish_rate);
		request.setAttribute("pass_rate", pass_rate);
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/finishPages/tongji.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
