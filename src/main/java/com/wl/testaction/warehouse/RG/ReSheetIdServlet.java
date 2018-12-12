package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.SheetId;
import com.wl.tools.CheckSheetId;

public class ReSheetIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReSheetIdServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CheckSheetId sheetid=new CheckSheetId();
		SheetId resheetid=sheetid.getResheetid();
		request.setAttribute("resheetid", resheetid);
		request.getRequestDispatcher("PO/Return.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
