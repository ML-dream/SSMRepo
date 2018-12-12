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

public class DoEditEmployeeDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String staffCode = ChineseCode.toUTF8(request.getParameter("staffCode").trim());
	    String staffName = ChineseCode.toUTF8(request.getParameter("staffName").trim());
	    String sectionCode = ChineseCode.toUTF8(request.getParameter("sectionCode").trim());
	    String gender = ChineseCode.toUTF8(request.getParameter("gender").trim());
	    String schoolFrom = ChineseCode.toUTF8(request.getParameter("schoolFrom").trim());
	    String address = ChineseCode.toUTF8(request.getParameter("address").trim());
	    String technicalGrade = ChineseCode.toUTF8(request.getParameter("technicalGrade").trim());
	    String birthday = ChineseCode.toUTF8(request.getParameter("birthday").trim());
	    String educationLevel = ChineseCode.toUTF8(request.getParameter("educationLevel").trim());
	    String speciality = ChineseCode.toUTF8(request.getParameter("speciality"));
	    String workType = ChineseCode.toUTF8(request.getParameter("workType"));
	    String officePhne = ChineseCode.toUTF8(request.getParameter("officePhne"));
	    String mobilePhone = ChineseCode.toUTF8(request.getParameter("mobilePhone").trim());
	    String position = ChineseCode.toUTF8(request.getParameter("position").trim());
	    String workTime = ChineseCode.toUTF8(request.getParameter("workTime"));
	    String QQ = ChineseCode.toUTF8(request.getParameter("QQ").trim());
	    String email = ChineseCode.toUTF8(request.getParameter("email").trim());
	    String homePhone = ChineseCode.toUTF8(request.getParameter("homePhone"));
	    String RFIDCode = ChineseCode.toUTF8(request.getParameter("RFIDCode").trim());
	    String joinTime = ChineseCode.toUTF8(request.getParameter("joinTime").trim());
	    String fee = ChineseCode.toUTF8(request.getParameter("fee").trim());
	    String leaveTime =ChineseCode.toUTF8(request.getParameter("leaveTime"));
	    String IDCard = ChineseCode.toUTF8(request.getParameter("IDCard").trim());
	    
	    String leave = request.getParameter("leave");
	    if("Y".equals(leave)){		//辞职了
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    	leaveTime = df.format(new Date());
	    }else {
			leaveTime = "";
		}
		String updateEmployeeSql = "update employee_info set " +
				"SECTION_CODE='"+sectionCode+"',EDUCATION_LEVEL='"+educationLevel+"'," +
				"SCHOOL_FROM='"+schoolFrom+"',SPECIALITY='"+speciality+"',WORK_TYPE='"+workType
				+"',TECHNICAL_GRADE='"+technicalGrade+"',ADDRESS='"+address+"',OFFICE_PHNE='"+officePhne+"'," +
				"MOBILE_PHONE='"+mobilePhone+"',HOME_PHONE='"+homePhone+"',POSITION='"+position+"',WORKTIME='"+workTime
				+"',RFID_CODE='"+RFIDCode+"',QQ='"+QQ+"',email='"+email+"'," +
				"FEE='"+fee+"',JOINTIME=to_date('"+joinTime+"','yyyy-mm-dd,hh24:mi:ss'),LEAVE='"+leave+"',leavetime=to_date('"+leaveTime+"','yyyy-mm-dd,hh24:mi:ss')" +
				"  where staff_code='"+staffCode+"'";
		System.out.println("updateEmployeeSql="+updateEmployeeSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(updateEmployeeSql);
			//request.setAttribute("addOk", "sucess");
			
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













