package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Return;
import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class editReServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editReServlet() {
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
		String reSheetid=request.getParameter("reSheetid");
		String reSql="select redate,resheetid,warehouseid,customerid,B.connector,B.connectortel,B.telephone,retype,B.account,paymethod,receipt," +
		"examineid,adminid,salesmanid,drawerid,C.warehouse_name warehouseName,D.companyname customerName,E.METHOD method,F.receiptname receiptName," +
		"G.staff_name examineName,H.staff_name adminName,K.staff_name salesmanName,P.staff_name drawerName from returngood B " +
			"left join warehouse C on C.warehouse_id=B.warehouseid " +
			"left join supplier D on D.companyid=B.customerid " +
			"left join paymethod E on E.id=B.paymethod " +
			"left join receipt F on F.receiptid=B.receipt " +
			"left join employee_info G on G.staff_code=B.examineid " +
			"left join employee_info H on H.staff_code=B.adminid " +
			"left join employee_info K on K.staff_code=B.salesmanid " +
			"left join employee_info P on P.staff_code=B.drawerid " +
			"where resheetid='"+reSheetid+"' order by resheetid";
		Return returngood=new Return();
		try{
			returngood=Sqlhelper.exeQueryBean(reSql, null, Return.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("returngood", returngood);
		request.getRequestDispatcher("PO/editRe.jsp").forward(request, response);
	}

}
