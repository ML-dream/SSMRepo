package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;

public class GoOutAssistMenuServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
      String waiXieCom=ChineseCode.toUTF8(request.getParameter("waiXieCom"));
      String companyName=ChineseCode.toUTF8(request.getParameter("companyName"));
      request.setAttribute("waiXieCom", waiXieCom);
      request.setAttribute("companyName",companyName);
      request.setAttribute("menuId", menuId);
      request.getRequestDispatcher("outAssistManage/outAssistMenu.jsp").forward(request,response);
      
	}

}
