package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.bom.entity.ItemType;

import com.wl.forms.payMethod;
import com.wl.tools.Sqlhelper;

public class GetItemTypeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetItemTypeServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String sql="select item_typeid id,item_typedesc text from itemtype";
		List<ItemType> resultList = new ArrayList<ItemType>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql, null, ItemType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		response.getWriter().append(json).flush();
		System.out.println(json);
		
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
