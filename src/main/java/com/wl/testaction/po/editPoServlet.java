package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Poplan;
import com.wl.tools.Sqlhelper;

public class editPoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String po_sheetid=request.getParameter("po_sheetid");
		String poSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
		"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername,status from po_plan B " +
		"left join employee_info C on C.staff_code=B.salesman_id " +
		"left join employee_info D on D.staff_code=B.drawerid " +
		"left join supplier G on G.companyid=B.customerid " +
		"where po_sheetid='"+po_sheetid+"'";
		Poplan poplan=new Poplan();
		try{
			poplan=Sqlhelper.exeQueryBean(poSql, null, Poplan.class);
			request.setAttribute("poplan", poplan);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/PO/editPo.jsp").forward(request, response);
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

		doGet(request,response);
	}

}
