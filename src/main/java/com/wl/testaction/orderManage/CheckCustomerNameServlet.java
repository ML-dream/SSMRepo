package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckCustomerNameServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String companyname=ChineseCode.toUTF8(request.getParameter("companyName"));
    String sql="select count(*) from customer where companyname=?";
    System.out.println(sql);
    String[] params={companyname};
    int count=0;
    try {
   	 
   count = Sqlhelper.exeQueryCountNum(sql, params);
	} catch (Exception e) {
		e.printStackTrace();
	}
    int totalcount=0;
    String Sql="select count(*) from customer ";
    String[] Params={};
    try{
     totalcount=Sqlhelper.exeQueryCountNum(Sql, Params);
    }catch(Exception e){
    	e.printStackTrace();
    }
    String json = "{\"total\":"+count+",\"totalcount\":"+totalcount+"}";
	response.setCharacterEncoding("UTF-8");
	response.getWriter().append(json).flush();
	System.out.println(json);
    
 
  }

	

}
