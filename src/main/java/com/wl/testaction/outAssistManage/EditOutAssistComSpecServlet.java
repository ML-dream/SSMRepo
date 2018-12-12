package com.wl.testaction.outAssistManage;


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


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EditOutAssistComSpecServlet extends HttpServlet {

	private static final long serialVersionUID = -2125593510580536066L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String companyId= ChineseCode.toUTF8(request.getParameter("companyId").trim());
		String companyName= ChineseCode.toUTF8(request.getParameter("companyName"));
		String foundingTime= ChineseCode.toUTF8(request.getParameter("foundingTime").trim());
		String employeeNum= ChineseCode.toUTF8(request.getParameter("employeeNum").trim());
		String type= ChineseCode.toUTF8(request.getParameter("type").trim());
		
		String address= ChineseCode.toUTF8(request.getParameter("address").trim());
		String postCode= ChineseCode.toUTF8(request.getParameter("postCode"));
		String telephone= ChineseCode.toUTF8(request.getParameter("telephone").trim());
		String webAddress= ChineseCode.toUTF8(request.getParameter("webAddress"));
		String header= ChineseCode.toUTF8(request.getParameter("header").trim());
		
		String business= ChineseCode.toUTF8(request.getParameter("business").trim());
		String connector= ChineseCode.toUTF8(request.getParameter("connector"));
		String connectorTel= ChineseCode.toUTF8(request.getParameter("connectorTel").trim());
		String connectorQQ= ChineseCode.toUTF8(request.getParameter("connectorQQ"));
		String connectorEmail= ChineseCode.toUTF8(request.getParameter("connectorEmail"));
		
		String bank= ChineseCode.toUTF8(request.getParameter("bank"));
		String account= ChineseCode.toUTF8(request.getParameter("account"));
		String dutyParagraph= ChineseCode.toUTF8(request.getParameter("dutyParagraph"));
		String founding= ChineseCode.toUTF8(request.getParameter("founding"));
	    String memo = ChineseCode.toUTF8(request.getParameter("memo"));
	    
	    String isTogether = ChineseCode.toUTF8(request.getParameter("isTogether").trim());
	    
	    String passRate = request.getParameter("passRate");
	    String connector2 = ChineseCode.toUTF8(request.getParameter("connector2"));
	    String connector2Tel = ChineseCode.toUTF8(request.getParameter("connector2Tel"));
	    
	    String connector3 = ChineseCode.toUTF8(request.getParameter("connector3"));
	    String connector3Tel = ChineseCode.toUTF8(request.getParameter("connector3Tel"));
	    
	    String connector4 = ChineseCode.toUTF8(request.getParameter("connector4"));
	    String connector4Tel = ChineseCode.toUTF8(request.getParameter("connector4Tel"));
	    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String sql = "update outAssistCom set " +
	    		"companyName='"+companyName+"',foundingTime=to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),employeeNum='"+employeeNum+"',type='"+type+"'," +
	    		"address='"+address+"',postCode='"+postCode+"',telephone='"+telephone+"',webAddress='"+webAddress+"',header='"+header+"'," +
	    		"business='"+business+"',connector='"+connector+"',connectorTel='"+connectorTel+"',connectorQQ='"+connectorQQ+"',connectorEmail='"+connectorEmail+"'," +
	    		"bank='"+bank+"',account='"+account+"',dutyParagraph='"+dutyParagraph+"',founding='"+founding+"',memo='"+memo+"',isTogether='"+isTogether+"' " +
	    				",passrate='"+passRate+"',connector2='"+connector2+"',connector2Tel='"+connector2Tel+"',connector3='"+connector3+"',connector3Tel='"+connector3Tel+"',connector4='"+connector4+"',connector4Tel='"+connector4Tel+"' "+
	    		"where companyId='"+companyId+"' and  connector='"+connector+"'";
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













