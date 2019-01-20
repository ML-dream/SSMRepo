package com.wl.testaction;


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

public class DoEditCustomerDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String companyId = request.getParameter("companyId").trim();
	    String companyName = request.getParameter("companyName").trim();
	    String type = request.getParameter("type").trim();
	    
	    String foundingTime = request.getParameter("foundingTime").trim();
	    String header = request.getParameter("header").trim();
	    String employeeNum = request.getParameter("employeeNum").trim();
	    
	    String address = request.getParameter("address").trim();
	    String postCode = request.getParameter("postCode").trim();
	    String telephone = request.getParameter("telephone").trim();
	    
	    String webAddress = request.getParameter("webAddress");
	    String business = request.getParameter("business").trim();
	    String advise = request.getParameter("advise");
	    String connector = "";
	    String connectorTel = "";
	    
	    String connector2 ="";
	    String connector2Tel ="";
	    String connector3 = "";
	    String connector3Tel = "";
	    
	    String connector4 = "";
	    String connector4Tel ="";
	    
	    String isTogether = request.getParameter("isTogether");

	    HttpSession session = request.getSession();
		String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String changeTime = df.format(new Date());
		
		String updateCustomerSql="update customer set " +
				"COMPANYNAME='"+companyName+"',FOUNDEINGTIME=to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),EMPLLOYEENUM='"+employeeNum+"',TYPE='"+type
				+"',ADDRESS='"+address+"',POSTCODE='"+postCode+"',TELEPHONE='"+telephone+"',changeperson='" +changePerson+"',changetime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'), "+
		    	"WEBADDRESS='"+webAddress+"',HEADER='"+header+"',BUSINESS='"+business+"',ADVISE='"+advise+"',ISTOGETHER='"+isTogether+"',connectorTel='" +connectorTel+"'" +
		    			",connector2='"+connector2+"',connector2Tel='"+connector2Tel+"',connector3='"+connector3+"',connector3Tel='"+connector3Tel+"',connector4='"+connector4+"',connector4Tel='"+connector4Tel+"' "+
		    	"where COMPANYID='"+companyId+"' and connector='"+connector+"'";
		System.out.println("updateCustomerSql="+updateCustomerSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(updateCustomerSql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			
			//request.getRequestDispatcher("employeeManage/showEmployee.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("addOk", "failure");
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













