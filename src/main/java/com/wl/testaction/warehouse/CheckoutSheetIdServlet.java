package com.wl.testaction.warehouse;

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

public class CheckoutSheetIdServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CheckSheetId checksheetid=new CheckSheetId();
		SheetId checksheet_id=checksheetid.getCheckoutsheetid();
		HttpSession session=request.getSession();
		String StaffCode = ((User)session.getAttribute("user")).getStaffCode();
		String StaffName = ((User)session.getAttribute("user")).getUserName();
		request.setAttribute("deliveryId", StaffCode);
		request.setAttribute("deliveryName", StaffName);
		request.setAttribute("checksheet_id", checksheet_id);
		request.getRequestDispatcher("/Checkout/Checkout.jsp").forward(request,response);
	}

}
