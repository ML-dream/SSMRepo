package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ReturnDetail;
import com.wl.tools.Sqlhelper;

public class editReDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editReDetailServlet() {
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
		String reSheetid=request.getParameter("reSheetid");
		String itemId=request.getParameter("itemId");
		String warehouseId=request.getParameter("warehouseId");
		String reSql="select * from redetail where resheetid=? and itemid=?";
		String[] params={reSheetid,itemId};
		ReturnDetail redetail=new ReturnDetail();
		try{
			redetail=Sqlhelper.exeQueryBean(reSql, params, ReturnDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("redetail", redetail);
		request.setAttribute("warehouseId", warehouseId);
		request.getRequestDispatcher("/PO/editReDetail.jsp").forward(request, response);
	}

}
