package com.wl.testaction.warehouse.supplier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Supplier;
import com.wl.tools.Sqlhelper;

public class editSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editSupplierServlet() {
		super();
	}

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
		String companyId=request.getParameter("companyId");
		String	SupplierSql = 
	    	"select COMPANYID,COMPANYNAME,FOUNDEINGTIME,EMPLLOYEENUM,TYPE,ADDRESS,POSTCODE,TELEPHONE," +
	    	"WEBADDRESS,HEADER,BUSINESS,DUTYPARAGRAPH,BANK,ACCOUNT,ADVISE,connector,connectorTel,rate " +
	    	"from supplier where companyId=?";
		String[] params={companyId};
		Supplier supplier=new Supplier();
		try{
			supplier=Sqlhelper.exeQueryBean(SupplierSql, params, Supplier.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("supplier",supplier);
		request.getRequestDispatcher("/Supplier/editSupplier.jsp").forward(request, response);
	}

}
