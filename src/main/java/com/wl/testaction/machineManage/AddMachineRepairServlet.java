package com.wl.testaction.machineManage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.UUIDHexGenerator;

public class AddMachineRepairServlet extends HttpServlet {

	private static final long serialVersionUID = 4262585196632425947L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String machineId = request.getParameter("machineId").trim();
//		String repairPart = ChineseCode.toUTF8(request.getParameter("repairPart").trim());

		String startDate = request.getParameter("startDate").trim().substring(0,10);
		String endDate = request.getParameter("endDate").trim().substring(0,10);
//		String repairPrice = request.getParameter("repairPrice").trim();
		String principal = request.getParameter("principal").trim();
		String repairDetail = request.getParameter("repairDetail");
		String memo = request.getParameter("memo");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());// new Date()为获取当前系统时间
		
/*		
		try {
			startDate = df.format(df.parse(startDate));
			endDate =df.format(df.parse(endDate));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		String UUID = UUIDHexGenerator.getInstance().generate();
		String sql = "insert into machineRepair " +
				"(mainId,machineId,startDate,endDate,createtime,repairDetail,principal,memo,repairState)" +
				"values('"+UUID+"','"+machineId+"','"+startDate+"','"+endDate+"'"+
				",'"+createTime+"','"+repairDetail+"','"+principal+"','"+memo+"','"+"是"+"') ";
		System.out.println("sql=="+sql);
		try {
			Sqlhelper.executeUpdate(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
		String json = "{\"result\":\"操作成功!\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		
/*		String updatesql = "insert into machineRepair " +
				"(mainId,machineId,repairFactory,errorDate,repairDate,repairPrice,principal,repairDetail,memo)" +
				"values('"+UUID+"','"+machineId+"','"+repairFactory+"',to_date('"+errorDate+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+repairDate+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+repairPrice+"','"+principal+"','"+repairDetail+"','"+memo+"') ";*/
	
	
	}


}
