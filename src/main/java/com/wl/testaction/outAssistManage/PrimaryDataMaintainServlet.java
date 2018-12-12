package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class PrimaryDataMaintainServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String modifyPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
	    String primaryTime=ChineseCode.toUTF8(request.getParameter("primaryTime"));
	    String payNum=ChineseCode.toUTF8(request.getParameter("payNum"));
	    String reason=ChineseCode.toUTF8(request.getParameter("reason"));
	    
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	    SimpleDateFormat tf=new SimpleDateFormat("HH:mm:ss");
	    String modifyTime = df.format(new Date());
//	    String Time=tf.format(new Date());
//	    primaryTime=primaryTime+" "+Time;
        
	    String insertSql="insert into outAssistPrimary values(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
	    String[] params={companyId,primaryTime,payNum,reason,modifyTime,modifyPerson};
	    try{
	    	Sqlhelper.executeUpdate(insertSql, params);
	    }catch(Exception e){
	      String json = "{\"result\":\"操作失败!\"}";
	  	  response.setCharacterEncoding("UTF-8");
	  	  response.getWriter().append(json).flush();
	  	  e.printStackTrace();
	    }
	 	 String json = "{\"result\":\"操作成功!\"}";
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().append(json).flush(); 
    
	}


}
