package com.wl.testaction.deptManage;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.e;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.wl.forms.Customer;
import com.wl.forms.Dept;
import com.wl.forms.Employee;
import com.wl.forms.Jiance;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class DeptSpecServlet extends HttpServlet {
	private static final long serialVersionUID = 8360075136244770184L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String deptId = ChineseCode.toUTF8(request.getParameter("deptId").trim());
	
		String	CustomerSql = 
		    	"select deptId,headStaffId,FDeptId,deptLevel,deptName,isKey,memo " +
		    	"from dept where deptId=? and isAlive='1' ";
		String[] params = {deptId};
		Dept result = new Dept();
		try {
			result = Sqlhelper.exeQueryBean(CustomerSql, params, Dept.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dept", result);
		
//	    ResultSet CustomerRs =null;
//		try{
//			CustomerRs = Sqlhelper.executeQuery(CustomerSql, null);
//			
//			CustomerRs.next();
//			Dept result = new Dept();
//			result.setDeptId(CustomerRs.getString(1));
//			result.setHeadStaffId(CustomerRs.getString(2));
//			result.setFDeptId(CustomerRs.getString(3));
//			result.setDeptLevel(CustomerRs.getInt(4));
//			result.setDeptName(CustomerRs.getString(5));
//			result.setIsKey(CustomerRs.getString(6));
//			result.setMemo(CustomerRs.getString(7));
//			
//			request.setAttribute("dept", result);
//			
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(CustomerRs!=null){
//					CustomerRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		request.getRequestDispatcher("deptManage/editDept.jsp").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













