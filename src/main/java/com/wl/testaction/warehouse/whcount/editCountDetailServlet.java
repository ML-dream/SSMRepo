package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.WhCountDetail;
import com.wl.tools.Sqlhelper;

public class editCountDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editCountDetailServlet() {
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
		String CountSheetId=request.getParameter("CountSheetId");
		String itemId=request.getParameter("itemId");
		String flag="edit";
		String sql="select * from whcountdetl where countsheetid=? and itemid=?";
		String[] params={CountSheetId,itemId};
		WhCountDetail whcountdetail=new WhCountDetail();
		try{
			
			whcountdetail=Sqlhelper.exeQueryBean(sql, params, WhCountDetail.class);
			whcountdetail.setFlag(flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(whcountdetail);
		out.append(json).flush();
		System.out.println(json);
		
	}

}
