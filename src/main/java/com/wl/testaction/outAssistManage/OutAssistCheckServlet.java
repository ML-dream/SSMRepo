package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistCheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     String menuId=ChineseCode.toUTF8(request.getParameter("menuId").trim());
     String outAssistStatus=ChineseCode.toUTF8(request.getParameter("outAssistStatus").trim());
     String checkedAdvice=ChineseCode.toUTF8(request.getParameter("checkedAdvice").trim());
     String checkAdvice=ChineseCode.toUTF8(request.getParameter("checkAdvice").trim());
	    HttpSession session = request.getSession();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    int status = 0;
	    try {
			status = Integer.parseInt(outAssistStatus);
		} catch (Exception e) {
			// TODO: handle exception
		}
		switch (status) {
		case 2:
			checkAdvice += "审核不通过";
			break;
		case 3:
			checkAdvice+= "通过";
			break;
		default:
			break;
		}
		checkAdvice=checkedAdvice+checkAdvice+"("+changePerson+")";
		String sql="update outassistmenu set outassistStatus=?,checkedadvice=? where menuId=?";
	    String[] params={outAssistStatus,checkAdvice,menuId};
 try{
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
