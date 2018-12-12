package com.wl.testaction.warehouse.applyexamine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Apply;
import com.wl.forms.Poplan;
import com.wl.tools.Sqlhelper;

public class showExamineApplyDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showExamineApplyDetailServlet() {
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
		String posheetid=request.getParameter("posheetid");
	
		String poSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
		"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername,opinion from po_plan B " +
		"left join employee_info C on C.staff_code=B.salesman_id " +
		"left join employee_info D on D.staff_code=B.drawerid " +
		"left join supplier G on G.companyid=B.customerid " +
		"where po_sheetid='"+posheetid+"'";
		
		Poplan poplan=new Poplan();
		
		try{
			poplan=Sqlhelper.exeQueryBean(poSql, null, Poplan.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("poplan", poplan);
		request.getRequestDispatcher("/PO/ExamineApplyDetail.jsp").forward(request, response);
	}

}
