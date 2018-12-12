package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.SheetId;
import com.wl.tools.CheckSheetId;

public class WhCountSheetIdServlet extends HttpServlet {

	public WhCountSheetIdServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CheckSheetId sheetid=new CheckSheetId();
		SheetId countsheetid=sheetid.getCountSheetId();
		request.setAttribute("countsheetid", countsheetid);
		request.getRequestDispatcher("WarehouseCount/WhCount.jsp").forward(request,response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
