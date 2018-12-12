package com.wl.testaction.outAssistManage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.tools.ChineseCode;
public class GoOutAssistDetailListServlet extends HttpServlet {

	private static final long serialVersionUID = -823478721838743897L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		request.setAttribute("orderId", orderId);
	    request.getRequestDispatcher("outAssistManage/showOutAssistSpec.jsp").forward(request, response);
	}


}
