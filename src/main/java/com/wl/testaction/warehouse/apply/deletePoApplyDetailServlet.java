package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class deletePoApplyDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public deletePoApplyDetailServlet() {
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

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String applySheetid=request.getParameter("applySheetid");
		String itemId=request.getParameter("itemId");
		
		String deleteSql="delete from poapplydetl where applysheetid='"+applySheetid+"' and itemid='"+itemId+"'";
		
		String itemSql="delete from itemcode where itemid='"+itemId+"'";
		
		try{
			Sqlhelper.executeUpdate(deleteSql, null);
			Sqlhelper.executeUpdate(itemSql, null);
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			e.printStackTrace();
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
		}
		
		
	}

}
