package com.wl.testaction.noticeManage;

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
import com.wl.tools.UUIDHexGenerator;

public class SendNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String receivers= ChineseCode.toUTF8(request.getParameter("receivers").trim());
		String noticeTitle= ChineseCode.toUTF8(request.getParameter("noticeTitle").trim());
		String grade= ChineseCode.toUTF8(request.getParameter("grade").trim());
		String content= ChineseCode.toUTF8(request.getParameter("content"));
		
		
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String[] receiverAll = receivers.split(";");
	    int count = 0;
		for (int i = 0; i < receiverAll.length; i++) {
			String uuid = UUIDHexGenerator.getInstance().generate();
			String receiver = receiverAll[i].substring(0, receiverAll[i].lastIndexOf("("));
			String sendNoticeSql = "insert into notice(id,sender,receiver,sendTime,contentTitle," +
	    		"content,grade)" +
	    		"values(?,?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,?,?)";
			String[] params = {uuid,createPerson,receiver,createTime,noticeTitle,content,grade};
			try {
				count += Sqlhelper.executeUpdate(sendNoticeSql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		if(count==receiverAll.length){
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}else {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
		
	}


}
