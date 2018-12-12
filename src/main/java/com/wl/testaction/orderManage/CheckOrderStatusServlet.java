package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckOrderStatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

          doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
       String sql="select order_Status from orders where order_Id=? ";
       String[] params={orderId};
       String productStatus=null;
       try{
    	   productStatus=Sqlhelper.exeQueryString(sql, params);
       }catch(Exception e){
    	e.printStackTrace();
       }
       
		String json = "{\"productStatus\":"+productStatus+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
       
	}



}
