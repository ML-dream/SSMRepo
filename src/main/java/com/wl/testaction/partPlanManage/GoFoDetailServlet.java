package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;

public class GoFoDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
    String productId=ChineseCode.toUTF8(request.getParameter("productId"));
    request.setAttribute("orderId", orderId);
    request.setAttribute("productId", productId);
    request.setAttribute("issueNum", issueNum);
    request.getRequestDispatcher("partPlanManage/checkFoDetail.jsp").forward(request, response);
	}



}
