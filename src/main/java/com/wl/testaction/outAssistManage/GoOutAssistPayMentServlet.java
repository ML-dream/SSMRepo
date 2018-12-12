package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;

public class GoOutAssistPayMentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
    String companyName=ChineseCode.toUTF8(request.getParameter("companyName"));
    request.setAttribute("companyId", companyId);
    request.setAttribute("companyName", companyName);
    request.getRequestDispatcher("outAssistManage/outAssistPayMent.jsp").forward(request, response);
	}


}
