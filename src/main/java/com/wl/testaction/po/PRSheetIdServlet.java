package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.SheetId;
import com.wl.tools.CheckSheetId;

public class PRSheetIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PRSheetIdServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CheckSheetId sheetid=new CheckSheetId();
		SheetId pr_sheetid=sheetid.getPrsheetid();
		request.setAttribute("pr_sheetid", pr_sheetid);
		request.getRequestDispatcher("PO/PR.jsp").forward(request,response);
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

		doPost(request,response);
	}

}
