package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

import com.wl.tools.ChineseCode;
public class EditMachineSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 2677624107788216049L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String machineId = ChineseCode.toUTF8(request.getParameter("machineId").trim());
//		String repairPart = ChineseCode.toUTF8(request.getParameter("repairPart").trim());
		//String repairFactory = "";
		String startDate = ChineseCode.toUTF8(request.getParameter("startDate").trim()).substring(0,10);;
		String endDate = ChineseCode.toUTF8(request.getParameter("endDate").trim()).substring(0,10);;
		//String repairPrice = ChineseCode.toUTF8(request.getParameter("repairPrice").trim());
		String principal = ChineseCode.toUTF8(request.getParameter("principal").trim());
		String repairDetail = ChineseCode.toUTF8(request.getParameter("repairDetail"));
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		String mainId = request.getParameter("mainId");
		
		String sql = "update machineRepair set " +
				
				"startDate=to_date('"+startDate+"','yyyy-mm-dd'),endDate=to_date('"+endDate+"','yyyy-mm-dd')," +
				"principal='"+principal+"',repairDetail='"+repairDetail+"',memo='"+memo+"' " +
				"where mainId='"+mainId+"'";
		System.out.println(sql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













