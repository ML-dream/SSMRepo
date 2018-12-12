package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckOutAssistStatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
    String productId=ChineseCode.toUTF8(request.getParameter("productId"));
    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
    String operId=ChineseCode.toUTF8(request.getParameter("operId"));
    String sql="select menuId from processesPlan where orderID=? and productId=? and issueNum=? and operId=?";
    String[] params={orderId,productId,issueNum,operId};
    ProcessesPlan process=new ProcessesPlan();
    try{
    	process=Sqlhelper.exeQueryBean(sql, params, ProcessesPlan.class);
    }catch(Exception e){
    	e.printStackTrace();
    }
    String Sql="select outAssistStatus from outAssistMenu where menuId=?";
    String[] Params={process.getMenuId()};
    String status=null;
    try{
    	status=Sqlhelper.exeQueryString(Sql, Params);
    }catch(Exception e){
    	e.printStackTrace();
    }
    String json = "{\"status\":"+status+"}";
	response.setCharacterEncoding("UTF-8");
	response.getWriter().append(json).flush();
  
	}


}
