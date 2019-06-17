package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.sql_data;

import com.wl.tools.ChineseCode;
public class EditMachineRepairSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 2677624107788216049L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String machineId = ChineseCode.toUTF8(request.getParameter("machineId").trim());
		String machineName = request.getParameter("machineName").trim();
		String machineSpec = request.getParameter("machineSpec").trim();
		String place = ChineseCode.toUTF8(request.getParameter("place").trim());
		String outCode = ChineseCode.toUTF8(request.getParameter("outCode").trim());
		
		String outDate = ChineseCode.toUTF8(request.getParameter("outDate").trim());
		String machNum = ChineseCode.toUTF8(request.getParameter("machNum").trim());
		String workRange = ChineseCode.toUTF8(request.getParameter("workRange").trim());
		String machType = request.getParameter("machType").trim();
		String machModel = request.getParameter("machModel").trim();
		
		String machStandard = ChineseCode.toUTF8(request.getParameter("machStandard").trim());
		String machManufacture = ChineseCode.toUTF8(request.getParameter("machManufacture").trim());
		String usedYears = ChineseCode.toUTF8(request.getParameter("usedYears").trim());
		String machPrice = ChineseCode.toUTF8(request.getParameter("machPrice").trim());
		String machOldRate = ChineseCode.toUTF8(request.getParameter("machOldRate").trim());
		
		String isKeyMach = ChineseCode.toUTF8(request.getParameter("isKeyMach").trim());
		String buyDate = ChineseCode.toUTF8(request.getParameter("buyDate").trim());
		String status = ChineseCode.toUTF8(request.getParameter("status").trim());
		String power = ChineseCode.toUTF8(request.getParameter("power").trim());
		String deptId = ChineseCode.toUTF8(request.getParameter("deptId"));
		
		String runDate = ChineseCode.toUTF8(request.getParameter("runDate"));
		String worker = ChineseCode.toUTF8(request.getParameter("worker"));
		String madeDate = ChineseCode.toUTF8(request.getParameter("madeDate"));
		String checkDate = ChineseCode.toUTF8(request.getParameter("checkTime"));
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		String hourPercent =ChineseCode.toUTF8(request.getParameter("hourPercent"));
		String countPercent=ChineseCode.toUTF8(request.getParameter("countPercent"));
		String sql = "update machinfo set machineName='"+machineName+"',machineSpec='"+machineSpec+"',place='"+place+"',outCode='"+outCode+"'," +
				"outDate=to_date('"+outDate+"','yyyy-mm-dd,hh24:mi:ss'),machNum='"+machNum+"',workRange='"+workRange+"',machType='"+machType+"',machModel='"+machModel+"'," +
				"machStandard='"+machStandard+"',machManufacture='"+machManufacture+"',usedYears='"+usedYears+"',machPrice='"+machPrice+"',machOldRate='"+machOldRate+"'," +
				"is_KeyMach='"+isKeyMach+"',buyDate=to_date('"+buyDate+"','yyyy-mm-dd,hh24:mi:ss'),status='"+status+"',power='"+power+"',dept_Id='"+deptId+"'," +
				"runDate=to_date('"+runDate+"','yyyy-mm-dd,hh24:mi:ss'),worker='"+worker+"',madeDate=to_date('"+madeDate+"','yyyy-mm-dd,hh24:mi:ss'),checkTime=to_date('"+checkDate+"','yyyy-mm-dd,hh24:mi:ss'),memo='"+memo+"' ," +
				"hourPercent='"+hourPercent+"',countPercent='"+countPercent+"'"+
				"where machineId='"+machineId+"'";
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













