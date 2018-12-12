package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ProcessOutAssistStatSaveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   String orderId=ChineseCode.toUTF8(request.getParameter("orderId").trim());
		    String productId=ChineseCode.toUTF8(request.getParameter("productId").trim());
		    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		    String operId=ChineseCode.toUTF8(request.getParameter("operId").trim());
		    String deliverTime=ChineseCode.toUTF8(request.getParameter("deliverTime").trim());
		    String planEndTime=ChineseCode.toUTF8(request.getParameter("planEndTime").trim());
		    String actuallyEndTime=ChineseCode.toUTF8(request.getParameter("actuallyEndTime").trim());
		    String unitPrice=ChineseCode.toUTF8(request.getParameter("unitPrice").trim());
		    String totalPrice=ChineseCode.toUTF8(request.getParameter("totalPrice").trim());
		    String quality=ChineseCode.toUTF8(request.getParameter("quality").trim());
		    String qualityFine=ChineseCode.toUTF8(request.getParameter("qualityFine").trim());
		    String alreadyPay=ChineseCode.toUTF8(request.getParameter("alreadyPay").trim());
		    String waitPay=ChineseCode.toUTF8(request.getParameter("waitPay").trim());
		    String isOpenTicket=ChineseCode.toUTF8(request.getParameter("isOpenTicket").trim());
		    String payTime=ChineseCode.toUTF8(request.getParameter("payTime").trim());
		    String isBusy=ChineseCode.toUTF8(request.getParameter("isBusy").trim());
		    String memo=ChineseCode.toUTF8(request.getParameter("memo").trim());
		    String sql="select count(*) from outAssistStat where orderId=? and productId=? and issueNum=? and operId=?";
		    String[] params={orderId,productId,issueNum,operId};
		    int count=0;
		    String Sql;
		    try{ 
		    	count=Sqlhelper.exeQueryCountNum(sql, params);
		       }catch(Exception e){
		    	e.printStackTrace();   
		       }
		    if(count==0)
		    {  Sql="insert into outassistStat(orderId,productId,issueNum,operId,deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo,totalPrice) " +
		    		"values(?,?,?,?,to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),?,?,?)";
		       String[] Params={orderId,productId,issueNum,operId,deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo,totalPrice};
		       try{
		     	    Sqlhelper.executeUpdate(Sql, Params);
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
		    else
		    {  Sql="update outAssistStat set deliverTime=to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),planEndTime=to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),actuallyEndTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),unitPrice=?,"+
		        "quality=?,qualityFine=?,alreadyPay=?,waitPay=?,isOpenTicket=?,"+
		        "payTime=to_date(?, 'yyyy-mm-dd,hh24:mi:ss'),isBusy=?,memo=?,totalPrice=? where orderId=? and productid=? and issueNum=? and operId=? ";
		       String[] Params={deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo,totalPrice,orderId,productId,issueNum,operId};
		       try{
		     	    Sqlhelper.executeUpdate(Sql, Params);
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


}
