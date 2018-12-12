package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class RemoveProcessFromOutAssistMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
    String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
    String productId=ChineseCode.toUTF8(request.getParameter("productId"));
    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
    String operId=ChineseCode.toUTF8(request.getParameter("operId"));
    String sql="select count(*) from processesPlan where menuId=?";
    String[] params={menuId};
    int count=0;
    try{
    	count =Sqlhelper.exeQueryCountNum(sql, params);
    }catch(Exception e){
    	e.printStackTrace();
    }
    String processesPlanSql=null;
    String outAssistStatSql=null;
    String outAssistMenuSql=null;
    String[] params1={menuId,orderId,productId,issueNum,operId};
    String[] params2={orderId,productId,issueNum,operId};
    String[] params3={menuId};
    
    if(count!=1){
      processesPlanSql="update processesPlan set waixiecom='',ismenu='',menuId='' where menuId=? and orderId=? and productId=? and issueNum=? and operId=?";
      outAssistStatSql=" delete from outAssistStat where orderId=? and productId=? and issueNum=? and operId=?";
      String TotalNumSql="update outassistmenu set totalnum=totalnum-1 where menuId=?";
      try{
    	  Sqlhelper.executeUpdate(processesPlanSql, params1);
    	  Sqlhelper.executeUpdate(outAssistStatSql, params2);
    	  Sqlhelper.executeUpdate(TotalNumSql, params3);
      	  String json = "{\"result\":\"操作成功!\",\"count\":"+count+"}";
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().append(json).flush();
      }catch(Exception e){
          String json = "{\"result\":\"操作失败!\"}";
  		  response.setCharacterEncoding("UTF-8");
  		  response.getWriter().append(json).flush();
  	      e.printStackTrace();
      }
    }
    else{
    	processesPlanSql="update processesPlan set waixiecom='',ismenu='',menuId='' where menuId=? and orderId=? and productId=? and issueNum=? and operId=?";
        outAssistStatSql="delete from outAssistStat where orderid=? and productId=? and issueNum=? and operId=?";
        outAssistMenuSql="delete from outAssistMenu where menuId=?"; 
        try{
      	  Sqlhelper.executeUpdate(processesPlanSql, params1);
      	  Sqlhelper.executeUpdate(outAssistStatSql, params2);
      	  Sqlhelper.executeUpdate(outAssistMenuSql, params3);
      	  String json = "{\"result\":\"操作成功!\",\"count\":"+count+"}";
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
	



}
