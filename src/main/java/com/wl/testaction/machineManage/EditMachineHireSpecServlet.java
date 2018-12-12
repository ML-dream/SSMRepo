package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.DateTimeUtil;
import com.wl.tools.StringUtil;
public class EditMachineHireSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 2389151904886707700L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String now = DateTimeUtil.formatDateToString(new Date(), DateTimeUtil.DATE_DAY_);
		String machineId = ChineseCode.toUTF8(request.getParameter("machineId").trim());
		String deptId = ChineseCode.toUTF8(request.getParameter("deptId").trim());
		String hireStatus = ChineseCode.toUTF8(request.getParameter("hireStatus").trim());
		String outDate = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("outDate"))?now:request.getParameter("outDate").trim());
		String inDate = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("inDate"))?now:request.getParameter("inDate").trim());
		String backDate = ChineseCode.toUTF8(request.getParameter("backDate").trim());
		String hireMoney = ChineseCode.toUTF8(request.getParameter("hireMoney").trim());
		String hireNum = ChineseCode.toUTF8(request.getParameter("hireNum").trim());
		String principal = ChineseCode.toUTF8(request.getParameter("principal").trim());
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
		String sql = "update machineHire set deptId='"+deptId+"',hireStatus='"+hireStatus+"'," +
				"outDate=to_date('"+outDate+"','yyyy-mm-dd,hh24:mi:ss'),inDate=to_date('"+inDate+"','yyyy-mm-dd,hh24:mi:ss'),backDate=to_date('"+backDate+"','yyyy-mm-dd,hh24:mi:ss')," +
				"hireMoney='"+hireMoney+"',hireNum='"+hireNum+"',principal='"+principal+"',memo='"+memo+"'," +
				"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss')" +
				"where machineId='"+machineId+"'";
		
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













