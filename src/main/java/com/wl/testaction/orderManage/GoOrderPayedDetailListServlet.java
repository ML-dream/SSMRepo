package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class GoOrderPayedDetailListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String productId =  ChineseCode.toUTF8(request.getParameter("productId").trim());
	    String fproductId =  ChineseCode.toUTF8(request.getParameter("fproductId").trim());
	    
		request.setAttribute("orderId", orderId);
		request.setAttribute("productId", productId);
		request.setAttribute("fproductId", fproductId);
	    request.getRequestDispatcher("orderManage/OrderPayedDetailList.jsp").forward(request, response);
	}


}
