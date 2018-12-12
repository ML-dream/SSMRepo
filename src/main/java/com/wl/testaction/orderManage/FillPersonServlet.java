package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class FillPersonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =((User)session.getAttribute("user")).getStaffCode();
		String Sql="select Staff_name staffname from employee_info where staff_code=?";
		String[] Params={staffCode};
        String staffName=null;
        try{
        	staffName=Sqlhelper.exeQueryString(Sql, Params);
        }catch(Exception e){
        	e.printStackTrace();
        }
		String json = "{\"staffCode\":\""+staffCode+"\",\"staffName\":\""+staffName+"\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);  
        
        
        
	}


}
