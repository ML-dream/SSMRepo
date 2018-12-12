package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Apply;
import com.wl.tools.Sqlhelper;

public class seePoApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public seePoApplyServlet() {
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
		request.setCharacterEncoding("utf-8");
		String applySheetid=request.getParameter("applySheetid");
		
		String applySql="select applydate applyDate,applysheetid applySheetid,applicantid applicantId,A.deptid,orderid,B.staff_name applicantName,c.deptname from apply A " +
				"left join employee_info B on B.staff_code=A.applicantid " +
				"left join dept C on C.deptid=A.deptid " +
				"where applysheetid='"+applySheetid+"'";
		
		Apply apply=new Apply();
		try{
			apply=Sqlhelper.exeQueryBean(applySql, null, Apply.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("apply", apply);
		request.getRequestDispatcher("/PO/seePoApply.jsp").forward(request, response);
		
	}

}
