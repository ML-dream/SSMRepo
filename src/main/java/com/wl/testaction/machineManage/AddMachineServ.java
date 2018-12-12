package com.wl.testaction.machineManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class AddMachineServ extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String machineId = request.getParameter("machineId").trim();
		String machineName =request.getParameter("machineName").trim();
		String machineSpec = request.getParameter("machineSpec").trim();
		String place =request.getParameter("place").trim();
		String outCode = request.getParameter("outCode").trim();
		
		String outDate = request.getParameter("outDate").trim();
		String machNum = request.getParameter("machNum").trim();
		String workRange = request.getParameter("workRange").trim();
		String machType = request.getParameter("machType").trim();
		String machModel = request.getParameter("machModel").trim();
		
		String machStandard =request.getParameter("machStandard").trim();
		String machManufacture = request.getParameter("machManufacture").trim();
		String usedYears = request.getParameter("usedYears").trim();
		String machPrice = request.getParameter("machPrice").trim();
		String machOldRate = request.getParameter("machOldRate").trim();
		
		String isKeyMach = request.getParameter("isKeyMach").trim();
		String buyDate = request.getParameter("buyDate").trim();
		String status =request.getParameter("status").trim();
		String power = request.getParameter("power").trim();
		String deptId = request.getParameter("deptId");
		
		String runDate = request.getParameter("runDate");
		String worker = request.getParameter("worker");
		String madeDate = request.getParameter("madeDate");
		String checkDate =request.getParameter("checkDate");
		String memo = request.getParameter("memo");
		String hourPercent = request.getParameter("hourPercent");
		String countPercent = request.getParameter("countPercent");
		
		String  sql = "insert into machinfo " +
				"(machineId,machineName,machineSpec,place,outCode," +
				"outDate,machNum,workRange,machType,machModel," +
				"machStandard,machManufacture,usedYears,machPrice,machOldRate," +
				"is_KeyMach,buyDate,status,power,dept_Id," +
				"runDate,worker,madeDate,checkTime,memo,hourPercent,countPercent)values('"+
				machineId+"','"+machineName+"','"+machineSpec+"','"+place+"','"+outCode+"'," +
				"to_date('"+outDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+machNum+"','"+workRange+"','"+machType+"','"+machModel+"','"+
				machStandard+"','"+machManufacture+"','"+usedYears+"','"+machPrice+"','"+machOldRate+"','"+
				isKeyMach+"',to_date('"+buyDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+status+"','"+power+"','"+deptId+"'," +
				"to_date('"+runDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+worker+"',to_date('"+madeDate+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+checkDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+memo+"','"+hourPercent+"','"+countPercent+"')";
		System.out.println("sql=="+sql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			request.setAttribute("result", "failure");
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}


}
