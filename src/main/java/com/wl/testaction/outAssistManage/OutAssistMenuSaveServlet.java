package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistMenuSaveServlet extends HttpServlet {
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
	      String planStartTime=ChineseCode.toUTF8(request.getParameter("planStartTime"));
	      planStartTime=planStartTime.substring(0,10);
	      String planEndTime=ChineseCode.toUTF8(request.getParameter("planEndTime"));
	      planEndTime=planEndTime.substring(0,10);
	      String unitPrice=ChineseCode.toUTF8(request.getParameter("unitPrice"));
	      String totalPrice=ChineseCode.toUTF8(request.getParameter("totalPrice"));
	      String memo=ChineseCode.toUTF8(request.getParameter("memo"));
	      String sql="update outAssistStat set deliverTime=to_date(?,'yyyy-mm-dd'),planEndTime=to_date(?,'yyyy-mm-dd'),unitPrice=?,totalPrice=?,memo=? "+
	    		    " where orderid=? and productId=? and issueNum=? and operId=?";
	      String[] params={planStartTime,planEndTime,unitPrice,totalPrice,memo,orderId,productId,issueNum,operId};
	      String sql1="select menuId from processesPlan where orderID=? and productId=? and issueNum=? and operId=?";
	      String[] params1={orderId,productId,issueNum,operId};
	      String Sql="update outAssistMenu set outAssistStatus='1' where menuId=?";
          ProcessesPlan process=new ProcessesPlan();
	      try{
	    	  Sqlhelper.executeUpdate(sql, params);
	    	  process=Sqlhelper.exeQueryBean(sql1, params1, ProcessesPlan.class);
	    	  String[] Params={process.getMenuId()};
	    	  Sqlhelper.executeUpdate(Sql, Params);
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
