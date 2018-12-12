package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPay;
import com.wl.tools.Sqlhelper;

public class seePayDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public seePayDetailServlet() {
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
		String paySheetid=request.getParameter("paySheetid");
		String paySql="select PAYDATE,PAYSHEETID,CUSTOMERID,C.COMPANYNAME customerName,B.CONNECTOR,B.CONNECTORTEL, " +
		"PAYTYPE,PAYABLE,PREPAID,PAYMENT,PAYMETHOD,D.METHOD method,B.ACCOUNT,EXAMINEID,SALESMANID,DRAWERID,ISBILL," +
		"E.STAFF_NAME examineName,F.STAFF_NAME salesmanName,G.STAFF_NAME drawerName from popay B " +
		"left join supplier C on C.companyid=B.customerid " +
		"left join paymethod D on D.id=B.paymethod " +
		"left join employee_info E on E.staff_code=B.examineid " +
		"left join employee_info F on F.staff_code=B.salesmanid " +
		"left join employee_info G on G.staff_code=B.drawerid where paysheetid=?";
		String[] params={paySheetid};
		PoPay popay=new PoPay();
		try{
			popay=Sqlhelper.exeQueryBean(paySql, params, PoPay.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("popay", popay);
		request.getRequestDispatcher("/PO/seeDetailPay.jsp").forward(request, response);
		

	}
}
