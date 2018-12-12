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

public class PoApplySheetIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoApplySheetIdServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
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
		request.getRequestDispatcher("PO/PoApply.jsp").forward(request,response);
	}

}
