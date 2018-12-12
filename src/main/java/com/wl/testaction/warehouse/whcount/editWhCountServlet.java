package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.LlSheet;
import com.wl.forms.User;
import com.wl.forms.WhCount;
import com.wl.tools.Sqlhelper;

public class editWhCountServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editWhCountServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String countSheetid=request.getParameter("countSheetid");
		HttpSession session=request.getSession();
		String editPerson=((User)session.getAttribute("user")).getStaffCode();
		String createSql="select createPerson from whcount where countsheetid='"+countSheetid+"'";
	    WhCount createPerson=new WhCount();
		String staffCode="";
		try{
			createPerson=Sqlhelper.exeQueryBean(createSql, null, WhCount.class);
			staffCode=createPerson.getCreatePerson();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(editPerson.equals(staffCode)){
		String countSql="select countSheetid,countDate,warehouseId,E.warehouse_name warehouseName,operatorId,C.staff_name operatorName,A.deptId,B.deptname deptName,empId,D.staff_name empName from whcount A " +
				"left join dept B on B.deptid=A.deptid " +
				"left join EMPLOYEE_INFO C on C.staff_code=A.operatorId " +
				"left join EMPLOYEE_INFO D on D.staff_code=A.empId " +
				"left join warehouse E on E.warehouse_id=A.warehouseId " +
				"where countSheetid='"+countSheetid+"'";
		WhCount whcount=new WhCount();
		try{
			whcount=Sqlhelper.exeQueryBean(countSql, null, WhCount.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("whcount", whcount);
		request.getRequestDispatcher("/WarehouseCount/editWhCount.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("Fail.jsp").forward(request, response);
		}
	}

}
