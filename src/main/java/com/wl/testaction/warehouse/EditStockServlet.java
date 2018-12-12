package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class EditStockServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
//		request.setCharacterEncoding("utf-8");
		String itemId=ChineseCode.toUTF8(request.getParameter("itemId"));
//		String itemId=request.getParameter("itemId");
		String sql="select * from stock where item_id='"+itemId+"'";
		StockInfo stockInfo=null;
		System.out.println("itemId"+itemId);
		try{
			stockInfo=Sqlhelper.exeQueryBean(sql,null,StockInfo.class);
			request.setAttribute("result", stockInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Stock/editStock.jsp").forward(request, response);
	}

}
