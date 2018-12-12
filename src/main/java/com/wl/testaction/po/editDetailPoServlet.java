package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoDetail;
import com.wl.tools.Sqlhelper;

public class editDetailPoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String po_sheetid=request.getParameter("po_sheetid");
		String item_id=request.getParameter("itemId");
		String poSql="select * from poplan_detl where po_sheetid='"+po_sheetid+"' and item_id='"+item_id+"'";
		PoDetail podetail=new PoDetail();
		try{
			podetail=Sqlhelper.exeQueryBean(poSql, null, PoDetail.class);
			request.setAttribute("podetail", podetail);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("/PO/editDetailPo.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
