package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class GoOutAssistListServlet extends HttpServlet {

	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
          doPost(request,response);
	}
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
         request.setAttribute("orderId", orderId);
         request.getRequestDispatcher("outAssistManage/outAssistSum.jsp").forward(request,response);    		
	}


}
