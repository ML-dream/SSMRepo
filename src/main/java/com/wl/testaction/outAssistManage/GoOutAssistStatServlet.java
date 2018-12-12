package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;

public class GoOutAssistStatServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      String waiXieCom=ChineseCode.toUTF8(request.getParameter("waiXieCom"));
      request.setAttribute("waiXieCom", waiXieCom);
      request.getRequestDispatcher("outAssistManage/outAssistStat.jsp").forward(request,response);
      
	}


}
