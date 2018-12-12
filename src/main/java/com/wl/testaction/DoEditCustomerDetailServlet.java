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

		String companyId = ChineseCode.toUTF8(request.getParameter("companyId").trim());
	    String companyName = ChineseCode.toUTF8(request.getParameter("companyName").trim());
	    String type = ChineseCode.toUTF8(request.getParameter("type").trim());
	    
	    String foundingTime = ChineseCode.toUTF8(request.getParameter("foundingTime").trim());
	    String header = ChineseCode.toUTF8(request.getParameter("header").trim());
	    String employeeNum = ChineseCode.toUTF8(request.getParameter("employeeNum").trim());
	    
	    String address = ChineseCode.toUTF8(request.getParameter("address").trim());
	    String postCode = ChineseCode.toUTF8(request.getParameter("postCode").trim());
	    String telephone = ChineseCode.toUTF8(request.getParameter("telephone").trim());
	    
	    String webAddress = ChineseCode.toUTF8(request.getParameter("webAddress"));
	    String business = ChineseCode.toUTF8(request.getParameter("business").trim());
	    String advise = ChineseCode.toUTF8(request.getParameter("advise"));
	    String connector = ChineseCode.toUTF8(request.getParameter("connector").trim());
	    String connectorTel = ChineseCode.toUTF8(request.getParameter("connectorTel").trim());
	    
	    String connector2 = ChineseCode.toUTF8(request.getParameter("connector2"));
	    String connector2Tel = ChineseCode.toUTF8(request.getParameter("connector2Tel"));
	    
	    String connector3 = ChineseCode.toUTF8(request.getParameter("connector3"));
	    String connector3Tel = ChineseCode.toUTF8(request.getParameter("connector3Tel"));
	    
	    String connector4 = ChineseCode.toUTF8(request.getParameter("connector4"));
	    String connector4Tel = ChineseCode.toUTF8(request.getParameter("connector4Tel"));
	    
	    String isTogether = request.getParameter("isTogether");
//	    if("Y".equals(leave)){		//辞职了
//	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	    	leaveTime = df.format(new Date());
//	    }else {
//			leaveTime = "";
//		}
//		String updateEmployeeSql = "update employee_info set " +
//				"SECTION_CODE='"+sectionCode+"',EDUCATION_LEVEL='"+educationLevel+"'," +
//				"SCHOOL_FROM='"+schoolFrom+"',SPECIALITY='"+speciality+"',WORK_TYPE='"+workType
//				+"',TECHNICAL_GRADE='"+technicalGrade+"',ADDRESS='"+address+"',OFFICE_PHNE='"+officePhne+"'," +
//				"MOBILE_PHONE='"+mobilePhone+"',HOME_PHONE='"+homePhone+"',POSITION='"+position+"',WORKTIME='"+workTime
//				+"',RFID_CODE='"+RFIDCode+"',QQ='"+QQ+"',email='"+email+"'," +
//				"FEE='"+fee+"',JOINTIME=to_date('"+joinTime+"','yyyy-mm-dd,hh24:mi:ss'),LEAVE='"+leave+"',leavetime=to_date('"+leaveTime+"','yyyy-mm-dd,hh24:mi:ss')" +
//				"  where staff_code='"+staffCode+"'";
//		System.out.println("updateEmployeeSql="+updateEmployeeSql);
		
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













