package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPayDetl;
import com.wl.tools.Sqlhelper;

public class editPayDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editPayDetailServlet() {
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
		String prSheetId=request.getParameter("prSheetId");
		String itemId=request.getParameter("itemId");
		String paySql="select * from popaydetl where PAYSHEETID=? and PRSHEETID=? and ITEMID=?";
		String[] params={paySheetid,prSheetId,itemId};
		PoPayDetl paydetail=new PoPayDetl();
		try{
			paydetail=Sqlhelper.exeQueryBean(paySql, params, PoPayDetl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("paydetail",paydetail);
		request.getRequestDispatcher("PO/editPayDetail.jsp").forward(request, response);
		
	}

}
