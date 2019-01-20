package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckOrderIdCountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
     String sql="select count(*) from orders where customer=?";
     String[] params={companyId};
     int count=0;
     try{
    	count=Sqlhelper.exeQueryCountNum(sql, params); 
     }catch(Exception e){
             e.printStackTrace();
     }
     String json = "{\"total\":"+count+"}";
 	 response.setCharacterEncoding("UTF-8");
 	 response.getWriter().append(json).flush();
 	 System.out.println(json); 
     
     
	}


}
