package com.wl.testaction.machineManage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

import com.wl.tools.ChineseCode;
import com.wl.tools.UUIDHexGenerator;

public class AddMachineRepairServlet extends HttpServlet {

	private static final long serialVersionUID = 4262585196632425947L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String machineId = ChineseCode.toUTF8(request.getParameter("machineId").trim());
//		String repairPart = ChineseCode.toUTF8(request.getParameter("repairPart").trim());
		String repairFactory = ChineseCode.toUTF8(request.getParameter("repairFactory").trim());
		String errorDate = ChineseCode.toUTF8(request.getParameter("errorDate").trim());
		String repairDate = ChineseCode.toUTF8(request.getParameter("repairDate").trim());
		String repairPrice = ChineseCode.toUTF8(request.getParameter("repairPrice").trim());
		String principal = ChineseCode.toUTF8(request.getParameter("principal").trim());
		String repairDetail = ChineseCode.toUTF8(request.getParameter("repairDetail"));
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));

		String UUID = UUIDHexGenerator.getInstance().generate();
		String sql = "insert into machineRepair " +
				"(mainId,machineId,repairFactory,errorDate,repairDate,repairPrice,principal,repairDetail,memo)" +
				"values('"+UUID+"','"+machineId+"','"+repairFactory+"',to_date('"+errorDate+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+repairDate+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+repairPrice+"','"+principal+"','"+repairDetail+"','"+memo+"') ";
		System.out.println("sql=="+sql);
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


}
