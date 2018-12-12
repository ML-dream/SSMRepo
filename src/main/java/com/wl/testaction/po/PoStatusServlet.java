package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class PoStatusServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoStatusServlet() {
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

		String data = ChineseCode.toUTF8(request.getParameter("data").trim());
		String[] applyValues = data.split(",");
		for (int i = 0; i < applyValues.length; i++) {
			String applySheetid = (applyValues[i].split("#"))[0];
			String isPass = (applyValues[i].split("#"))[1];
			String sql="update apply set ispass='"+isPass+"' where applysheetid='"+applySheetid+"'";
			try{
				Sqlhelper.executeUpdate(sql, null);
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}catch(Exception e){
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				e.printStackTrace();
			}
			
		}
		
	}

}
