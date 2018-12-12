package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ProductOutAssistSaveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
         String productId=ChineseCode.toUTF8(request.getParameter("productId"));
         String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
         String WaiXieCom=ChineseCode.toUTF8(request.getParameter("WaiXieCom"));
         String sql="update processesplan set waixiecom="+WaiXieCom+" where orderId=? and productId=? and issueNum=? and isco='1'";
         String[] params={orderId,productId,issueNum};
        try {
        	Sqlhelper.executeUpdate(sql, params);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
  		 
	}



}