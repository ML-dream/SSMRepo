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

public class OutAssistStatSaveServlet extends HttpServlet {

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
    String deliverTime=ChineseCode.toUTF8(request.getParameter("deliverTime"));
    String planEndTime=ChineseCode.toUTF8(request.getParameter("planEndTime"));
    String actuallyEndTime=ChineseCode.toUTF8(request.getParameter("actuallyEndTime"));
    String unitPrice=ChineseCode.toUTF8(request.getParameter("unitPrice"));
    String quality=ChineseCode.toUTF8(request.getParameter("quality"));
    String qualityFine=ChineseCode.toUTF8(request.getParameter("qualityFine"));
    String alreadyPay=ChineseCode.toUTF8(request.getParameter("alreadyPay"));
    String waitPay=ChineseCode.toUTF8(request.getParameter("waitPay"));
    String isOpenTicket=ChineseCode.toUTF8(request.getParameter("isOpenTicket"));
    String payTime=ChineseCode.toUTF8(request.getParameter("payTime"));
    String isBusy=ChineseCode.toUTF8(request.getParameter("isBusy"));
    String memo=ChineseCode.toUTF8(request.getParameter("memo"));
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
    {  Sql="insert into outassistStat values(?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
       String[] Params={orderId,productId,issueNum,operId,deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo};
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
    {  Sql="update outAssistStat set deliverTime=to_date(?,'yyyy-mm-dd'),planEndTime=to_date(?,'yyyy-mm-dd'),actuallyEndTime=to_date(?,'yyyy-mm-dd'),unitPrice=?,"+
        "quality=?,qualityFine=?,alreadyPay=?,waitPay=?,isOpenTicket=?,"+
        "payTime=to_date(?,'yyyy-mm-dd'),isBusy=?,memo=? where orderId=? and productid=? and issueNum=? and operId=? ";
       String[] Params={deliverTime,planEndTime,actuallyEndTime,unitPrice,quality,qualityFine,alreadyPay,waitPay,isOpenTicket,payTime,isBusy,memo,orderId,productId,issueNum,operId};
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
