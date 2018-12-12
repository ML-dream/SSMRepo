package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.wl.tools.Sqlhelper;

public class AddOutAssistComServlet extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
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
	    
	    String sql = "insert into outAssistCom" +
	    		"(companyId,companyName,foundingTime,employeeNum,type," +
	    		"address,postCode,telephone,webAddress,header," +
	    		"business,connector,connectorTel,connectorQQ,connectorEmail," +
	    		"bank,account,dutyParagraph,founding,memo,passrate,connector2,connector2Tel,connector3,connector3Tel,connector4,connector4Tel)" +
	    		"values('"+companyId+"','"+companyName+"',to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+employeeNum+"','"+type+"'," +
	    		"'"+address+"','"+postCode+"','"+telephone+"','"+webAddress+"','"+header+"'," +
	    		"'"+business+"','"+connector+"','"+connectorTel+"','"+connectorQQ+"','"+connectorEmail+"'," +
	    		"'"+bank+"','"+account+"','"+dutyParagraph+"','"+founding+"','"+memo+"','"+passRate+"','"+connector2+"','"+connector2Tel+"','"+connector3+"','"+connector3Tel+"','"+connector4+"','"+connector4Tel+"')";
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


}
