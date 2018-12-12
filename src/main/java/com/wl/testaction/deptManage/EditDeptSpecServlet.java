package com.wl.testaction.deptManage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;
/*import oracle.net.aso.l;*/

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EditDeptSpecServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String deptId = ChineseCode.toUTF8(request.getParameter("deptId").trim());
		String deptName = ChineseCode.toUTF8(request.getParameter("deptName").trim());
		String FDeptId = ChineseCode.toUTF8(request.getParameter("FDeptId").trim());
		String deptLevel = ChineseCode.toUTF8(request.getParameter("deptLevel").trim());
		String headStaffId = ChineseCode.toUTF8(request.getParameter("headStaffId"));
		String isKey = ChineseCode.toUTF8(request.getParameter("isKey").trim());
		String isAlive = ChineseCode.toUTF8(request.getParameter("isAlive").trim());
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
	    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		
//		String updateCustomerSql="update customer set " +
//				"COMPANYNAME='"+companyName+"',FOUNDEINGTIME=to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),EMPLLOYEENUM='"+employeeNum+"',TYPE='"+type
//				+"',ADDRESS='"+address+"',POSTCODE='"+postCode+"',TELEPHONE='"+telephone+"'," +
//		    	"WEBADDRESS='"+webAddress+"',HEADER='"+header+"',BUSINESS='"+business+"',ADVISE='"+advise+"',ISTOGETHER='"+isTogether+"',connectorTel='" +connectorTel+"'"+
//		    	"where COMPANYID='"+companyId+"' and connector='"+connector+"'";
	    
	    String sql = "update dept set deptName='"+deptName+"',FDeptId='"+FDeptId+"',deptLevel='"+deptLevel+"',headStaffId='"+headStaffId+"',isKey='"+isKey+"'," +
	    		"memo='"+memo+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),changePerson='"+changePerson+"',isAlive='"+isAlive+"' " +
	    		"where deptId='"+deptId+"' ";
		System.out.println("sql="+sql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













