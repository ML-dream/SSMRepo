package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.SheetId;
import com.wl.tools.CheckSheetId;

public class PaySheetIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PaySheetIdServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CheckSheetId sheetid=new CheckSheetId();
		SheetId paysheetid=sheetid.getPaysheetid();
		request.setAttribute("paysheetid", paysheetid);
		request.getRequestDispatcher("PO/Pay.jsp").forward(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
