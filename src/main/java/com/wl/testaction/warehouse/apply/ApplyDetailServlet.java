package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Apply;
import com.wl.tools.Sqlhelper;

public class ApplyDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ApplyDetailServlet() {
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
		String applySheetid=request.getParameter("applySheetid");
		
		String applySql="select APPLYDATE,APPLYSHEETID,APPLICANTID,C.STAFF_NAME applicantName,B.DEPTID,D.DEPTNAME deptName,ISPASS,ORDERID,WAREHOUSEID,E.warehouse_name warehouseName " +
		"from apply B " +
		"left join EMPLOYEE_INFO C on C.staff_code=B.applicantid " +
		"left join dept D on D.deptid=B.deptid " +
		"left join warehouse E on E.warehouse_id=B.warehouseid " +
		"where applysheetid='"+applySheetid+"'";
		
		Apply apply=new Apply();
		
		try{
			apply=Sqlhelper.exeQueryBean(applySql, null, Apply.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("apply", apply);
		request.getRequestDispatcher("/PO/ApplyDetail.jsp").forward(request, response);
	}

}
