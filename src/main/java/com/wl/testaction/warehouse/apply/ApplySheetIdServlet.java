package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.SheetId;
import com.wl.forms.User;
import com.wl.tools.CheckSheetId;

public class ApplySheetIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ApplySheetIdServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CheckSheetId sheetid=new CheckSheetId();
		SheetId applysheetid=sheetid.getApplySheetId();
		HttpSession session = request.getSession();
		String StaffCode = ((User)session.getAttribute("user")).getStaffCode();
		String StaffName = ((User)session.getAttribute("user")).getUserName();
		request.setAttribute("applysheetid", applysheetid);
		request.setAttribute("StaffCode", StaffCode);
		request.setAttribute("StaffName", StaffName);
		request.getRequestDispatcher("PO/Apply.jsp").forward(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
