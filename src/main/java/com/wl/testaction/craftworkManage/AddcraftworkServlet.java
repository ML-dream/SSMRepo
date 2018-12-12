package com.wl.testaction.craftworkManage;

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
import com.wl.tools.UUIDHexGenerator;

public class AddcraftworkServlet extends HttpServlet {

	private static final long serialVersionUID = 4262585196632425947L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String machineId = ChineseCode.toUTF8(request.getParameter("machineId").trim());
		String deptId = ChineseCode.toUTF8(request.getParameter("deptId").trim());
		String discardDate = ChineseCode.toUTF8(request.getParameter("discardDate").trim());
		String discardTo = ChineseCode.toUTF8(request.getParameter("discardTo").trim());
		String discardMoney = ChineseCode.toUTF8(request.getParameter("discardMoney").trim());
		String principal = ChineseCode.toUTF8(request.getParameter("principal").trim());		
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
		String UUID = UUIDHexGenerator.getInstance().generate();
		String sql = "insert into machineDiscard " +
				"(mainId,machineId,deptId,discardDate,discardTo,discardMoney,principal,memo," +
				"createPerson,createTime,changePerson,changeTime)" +
				"values('"+UUID+"','"+machineId+"','"+deptId+"',to_date('"+discardDate+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+discardTo+"','"+discardMoney+"','"+principal+"','"+memo+"'," +
				"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss')) ";
		System.out.println("sql=="+sql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}


}
