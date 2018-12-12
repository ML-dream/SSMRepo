package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.Sqlhelper;

public class DeleteOutAssistMenuBeforeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      String menuSql="select menuId from outAssistMenu where deliverTime<to_date('2017-04-01','yyyy-mm-dd')";
      String deleteMenuSql="delete from outAssistMenu where menuId=?";
      String processSql="select orderId,productId,issueNum,operId from processesPlan where menuId=?";
      String processUpdateSql="update ProcessesPlan set ismenu='0', waixieCom='',menuId='' where menuId=?";
      String deleteStatSql="delete from outassistStat where orderId=? and productId=? and issuenum=? and operId=? ";
      List<ProcessesPlan> menuList=new ArrayList<ProcessesPlan>();
      List<ProcessesPlan> processList=new ArrayList<ProcessesPlan>();
      try{
    	menuList=Sqlhelper.exeQueryList(menuSql, null, ProcessesPlan.class);    
	 }catch(Exception e){
		e.printStackTrace();
	 }
     for(int i=0;i<menuList.size();i++){
      ProcessesPlan menu=menuList.get(i);
      String[] params={menu.getMenuId()};
      try{
    	  processList=Sqlhelper.exeQueryList(processSql, params, ProcessesPlan.class);
    	  Sqlhelper.executeUpdate(processUpdateSql, params);
    	  Sqlhelper.executeUpdate(deleteMenuSql, params);
      }catch(Exception e){
    	  e.printStackTrace();
      }
      
         for(int j=0;j<processList.size();j++){
    	  ProcessesPlan process=processList.get(j);
    	  String[] Params={process.getOrderId(),process.getProductId(),process.getIssueNum(),process.getOperId()};
    	  try {
			Sqlhelper.executeUpdate(deleteStatSql, Params);
		} catch (SQLException e) {
			e.printStackTrace();
		 }
       } 
     }
     String json = "{\"result\":\"操作成功!\"}";
	 response.setCharacterEncoding("UTF-8");
	 response.getWriter().append(json).flush(); 
 
	}


}
