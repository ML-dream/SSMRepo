package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class editUnpassPrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editUnpassPrServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		doPost(request, response);
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

	//  查看审核未通过详情
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String prSheetid=request.getParameter("prSheetid");
		String prSql="select B.purid,B.purstate, PRDATE,PRSHEETID,CUSTOMERID,C.COMPANYNAME customerName," +
		"B.CONNECTOR,B.CONNECTORTEL,B.PAYTERM,B.DUTYPARAGRAPH,B.BANK,B.ACCOUNT,EXAMINEID,SALESMANID,DRAWERID," +
		"D.STAFF_NAME examineName,E.STAFF_NAME adminName,F.STAFF_NAME salesmanName,G.STAFF_NAME drawerName,ISBILL,RECEIPT " +
		"from pr B left join warehouse W on W.warehouse_id=B.warehouseid " +
		"left join supplier C on C.companyid=B.customerid " +
		"left join employee_info D on D.staff_code=B.examineid " +
		"left join employee_info E on E.staff_code=B.adminid " +
		"left join employee_info F on F.staff_code=B.salesmanid " +
		"left join employee_info G on G.staff_code=B.drawerid " +
		"where prsheetid='"+prSheetid+"'";
		pr prsheet=new pr();
		try{
			prsheet=Sqlhelper.exeQueryBean(prSql, null, pr.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("prsheet", prsheet);
		request.getRequestDispatcher("PO/seeUnpassPrIn.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
