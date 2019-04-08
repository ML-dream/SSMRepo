package com.wl.testaction;

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

public class AddEmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String staffCode = ChineseCode.toUTF8(request.getParameter("staffCode").trim());
	    String staffName = request.getParameter("staffName").trim();
	    String sectionCode = ChineseCode.toUTF8(request.getParameter("sectionCode").trim());
	    String gender = ChineseCode.toUTF8(request.getParameter("gender").trim());
	    String address = ChineseCode.toUTF8(request.getParameter("address").trim());
	    String birthday = ChineseCode.toUTF8(request.getParameter("birthday").trim());
	    String educationLevel = ChineseCode.toUTF8(request.getParameter("educationLevel").trim());
	    String officePhne = ChineseCode.toUTF8(request.getParameter("officePhne"));
	    String mobilePhone = ChineseCode.toUTF8(request.getParameter("mobilePhone").trim());
	   
	    String QQ = ChineseCode.toUTF8(request.getParameter("QQ").trim());
	    String email = ChineseCode.toUTF8(request.getParameter("email").trim());
	  
		String  addEmployeeSql = "insert into employee_info " +
				"(staff_code,STAFF_NAME,SECTION_CODE,GENDER,EDUCATION_LEVEL," +
				"ADDRESS,OFFICE_PHNE," +
				"MOBILE_PHONE,QQ,email,birthday," +
				"LEAVE )values('"+
				staffCode+"','"+staffName+"','"+sectionCode+"','"+gender+"','"
				+educationLevel+
				address+"','"+officePhne+"','"+mobilePhone+"','"+
				QQ+"','"+email+"',to_date('"+birthday+"','yyyy-mm-dd,hh24:mi:ss'),'"
				+"','N')";
		System.out.println("addMachineSql=="+addEmployeeSql);  
		
		//######################################################################//
		
		/*String companyId = request.getParameter("companyId").trim();
		
		
		String  addCustomerSql = "insert into customer " +
				"(COMPANYID,COMPANYNAME," +
				"ADDRESS,TELEPHONE,TYPE,connector,connectorTel,ISTOGETHER )values('"+
				companyId+"','"+staffName+"','"+address+"','"+mobilePhone+"','GQ','"+staffName+"','"+mobilePhone+"','Y')";
		System.out.println("addMachineSql=="+addCustomerSql);*/
		sql_data sqlData = new sql_data();
		
		
		//##########################################################################################//
//		sql_data sqlData = new sql_data();
		String result = "保存成功";
		try {
//			sqlData.exeUpdateThrowExcep(addEmployeeSql);
			Sqlhelper.executeUpdate(addEmployeeSql, null);
//			request.setAttribute("addOk", "sucess");

//			request.getRequestDispatcher("employeeManage/editEmployee.jsp").forward(request, response);
		} catch (SQLException e) {
			result = "保存失败";
//			request.setAttribute("addOk", "failure");
			e.printStackTrace();
		}
		/*try {
			sqlData.exeUpdateThrowExcep(addCustomerSql);
			String json = "{\"result\":\"操作成功!\"}";
			
		} catch (SQLException e) {
			result = "保存失败2";
			e.printStackTrace();
		}*/
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(result).flush();
		//this.getServletConfig().getServletContext().getRequestDispatcher("/UserManage.jsp").forward(request, response);
	}

		private String isNull(String para) {
			
		return	para==null?"":para;
			
			
		}
}
