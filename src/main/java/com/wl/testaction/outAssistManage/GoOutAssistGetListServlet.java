package com.wl.testaction.outAssistManage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.tools.ChineseCode;
public class GoOutAssistGetListServlet extends HttpServlet {

	private static final long serialVersionUID = 6221852125961149518L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String itemId = ChineseCode.toUTF8(request.getParameter("itemId").trim());
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
		request.setAttribute("orderId", orderId);
		request.setAttribute("itemId", itemId);
		request.setAttribute("drawingId", drawingId);
	    request.getRequestDispatcher("outAssistManage/showOutAssistGet.jsp").forward(request, response);
	}


}
