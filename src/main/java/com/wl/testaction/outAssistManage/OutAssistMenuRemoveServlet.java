package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistMenuRemoveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
    String[] params={menuId};
    String Sql="select orderId,productId,issueNum,operId from processesPlan where menuId=?";
    List<ProcessesPlan> ProcessesDetail=new ArrayList<ProcessesPlan>();
    try{
    	ProcessesDetail=Sqlhelper.exeQueryList(Sql, params, ProcessesPlan.class);
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    String OutAssistStatSql="delete from outAssistStat where orderId=? and productId=? and issueNum=? and operId=?";
    
    for(int i=0;i<ProcessesDetail.size();i++){
       ProcessesPlan process=ProcessesDetail.get(i);
       String[] Params={process.getOrderId(),process.getProductId(),process.getIssueNum(),process.getOperId()};
       try{ 
    	  Sqlhelper.executeUpdate(OutAssistStatSql, Params);
       }catch(Exception e){
    	   e.printStackTrace();
       }
    
    }
    String ProcessesPlanSql="update processesPlan set waixiecom='',ismenu='',menuId='' where menuId=?";
    String OutAssistMenuSql="delete from outAssistMenu where menuId=?"; 
    try{
    	Sqlhelper.executeUpdate(ProcessesPlanSql, params);
    	Sqlhelper.executeUpdate(OutAssistMenuSql, params);
  	    String json = "{\"result\":\"操作成功!\"}";
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().append(json).flush();
    }catch(Exception e){
		  String json = "{\"result\":\"操作失败!\"}";
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().append(json).flush();
	      e.printStackTrace();
    }
    
	}


}