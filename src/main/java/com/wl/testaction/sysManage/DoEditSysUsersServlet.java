package com.wl.testaction.sysManage;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.MD5;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

import cfmes.util.sql_data;

public class DoEditSysUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 8159747285567002962L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String userId = ChineseCode.toUTF8(request.getParameter("userId").trim());
		HttpSession session = request.getSession();
	    String sessionPassword = ((User)(session.getAttribute("user"))).getPassword();//???
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String changeTime = df.format(new Date());
	    
	    String authoritySql = "";
	    
//		参数1 表示重置密码，xiem
		String para = "";
		para =StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");
		if(!para.isEmpty()&&para.equals("1")){
			String newPassWord ="123";//MD5一种加密算法
			authoritySql = "update sysusers set PASSWORD='"+MD5.MD5Convert(newPassWord)+"',changePerson='"+changePerson+"'," +
			"changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),PWD_MODIFY_TIME=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
			"where USER_ID='"+userId+"'";
			
			try {
				Sqlhelper.executeUpdate(authoritySql, null);
				System.out.println("ok  "+authoritySql);
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}
			return;
			
		}
		
	    String staffCode = ChineseCode.toUTF8(request.getParameter("staffCode").trim());
	    String userName = ChineseCode.toUTF8(request.getParameter("userName").trim());
	    String password = ChineseCode.toUTF8(request.getParameter("password"));
	    String newpassword = ChineseCode.toUTF8(request.getParameter("newpassword"));
	    String Confirmpassword = ChineseCode.toUTF8(request.getParameter("Confirmpassword"));
	    String authority = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("authority"))?"0":request.getParameter("authority").trim());
	    
	    
	    if (StringUtil.isNullOrEmpty(password)||StringUtil.isNullOrEmpty(newpassword)||StringUtil.isNullOrEmpty(Confirmpassword)) {
	    	authoritySql = "update sysusers set AUTHORITY='"+authority+"', changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
	    			"where USER_ID='"+userId+"'";
		}else {
			if (!StringUtil.isNullOrEmpty(password)&&sessionPassword.equals(MD5.MD5Convert(password))) {		//填写了密码,且正确
				authoritySql = "update sysusers set PASSWORD='"+MD5.MD5Convert(newpassword)+"', OLD_PASSWORD='"+sessionPassword+"',changePerson='"+changePerson+"'," +
					"changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),PWD_MODIFY_TIME=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
					"where USER_ID='"+userId+"'";
			}
		}
		
		System.out.println("authoritySql="+authoritySql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(authoritySql);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













