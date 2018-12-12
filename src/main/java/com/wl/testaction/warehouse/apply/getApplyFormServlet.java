package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.tools.Sqlhelper;

public class getApplyFormServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getApplyFormServlet() {
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
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String itemId=request.getParameter("itemId");
		
		String Sql="select item_id,item_name,spec,stock_num,unit,item_type from stock where item_id='"+itemId+"'";
		StockInfo stock=new StockInfo();
		try{
			stock=Sqlhelper.exeQueryBean(Sql, null, StockInfo.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(stock);
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
