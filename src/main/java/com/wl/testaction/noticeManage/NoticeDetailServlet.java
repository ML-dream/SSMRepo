package com.wl.testaction.noticeManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.Notice;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String id =  ChineseCode.toUTF8(request.getParameter("id").trim());
		String isReaded =  ChineseCode.toUTF8(request.getParameter("isReaded").trim());
		
		System.out.println("id=="+id+";isReaded=="+isReaded);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
		
		String noticeReadSql = "update notice set isReaded='1',readTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss') where id=? ";
		String[] noticeReadParams = {createTime,id};
		
	    String noticeWillSql = "select id,B.staff_name sender,C.staff_name receiver,sendTime,readTime," +
	    		"contentTitle,content,attachment,isReaded,A.grade grade " +
	    		"from notice A " +
	    		"left join employee_info B on A.sender=B.staff_code " +
	    		"left join employee_info C on A.receiver=C.staff_code  " +
	    		"where id=?";
	    String[] params = {id};
	    Notice result = new Notice();
		try {
			result = Sqlhelper.exeQueryBean(noticeWillSql, params, Notice.class);
			if("0".equals(isReaded)){
				Sqlhelper.executeUpdate(noticeReadSql, noticeReadParams);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("noticeManage/noticeDetail.jsp").forward(request, response);
	}


}
