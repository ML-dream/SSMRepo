package com.wl.testaction.warehouse.RM;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.TlSheetDetail;
import com.wl.tools.Sqlhelper;

public class editTlSheetForm extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String rmSheetid=request.getParameter("rmSheetid");
		String itemId=request.getParameter("itemId");
		String warehouse_id=request.getParameter("warehouse_id");
		String[] params={rmSheetid,itemId};
		String sql="select * from tuiliao_detl where rmSheetid=? and itemId=?";
		TlSheetDetail tlsheetdetail=new TlSheetDetail();
		try{
			tlsheetdetail=Sqlhelper.exeQueryBean(sql, params, TlSheetDetail.class);
			request.setAttribute("tlsheetdetail", tlsheetdetail);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("warehouse_id", warehouse_id);
		request.getRequestDispatcher("/Tuiliao/editTlSheetDetail.jsp").forward(request, response);
	}

}
