package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.wl.forms.Order;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistSumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
              doPost(request,response);
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object data=request.getSession().getAttribute("allOutAssist");
	    JSONArray jsonArray = JSONArray.fromObject(data);
		List<Order> orders = JSONArray.toList(jsonArray, Order.class);
		List<ProcessesPlan> AllOutAssistCom=new ArrayList<ProcessesPlan>();
		String sql="select distinct A.waixieCom,B.companyName from processesplan A " +
     		   "left join outAssistCom B ON A.waixieCom=B.companyId "  +
     		   "where orderId=? and productId=? and issueNum=? and isco='1'";
        for(int i=0;i<orders.size();i++){
           Order order=orders.get(i);  
           String[] params={order.getOrderId(),order.getProductId(),order.getIssueNum()};
           List<ProcessesPlan> OutAssistCom=new ArrayList<ProcessesPlan>();
           try{
        	   OutAssistCom=Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
           }catch(Exception e){
        	   e.printStackTrace();
           }
        	AllOutAssistCom.addAll(OutAssistCom);
           }
        
          List<ProcessesPlan> AllOutAssistCom1=new ArrayList<ProcessesPlan>();
          AllOutAssistCom1.add(AllOutAssistCom.get(0));
          for(int j=1;j<AllOutAssistCom.size();j++){
        	  for(int k=0;k<=j;k++){
        	  if((AllOutAssistCom.get(j).getWaiXieCom()).equals(AllOutAssistCom.get(k).getWaiXieCom()))
        		  AllOutAssistCom1.add(AllOutAssistCom.get(j));
        	  }
        		 
          }
        	 
	      String json = PluSoft.Utils.JSON.Encode(AllOutAssistCom);
		  json = "{\"data\":"+json+"}";
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().append(json).flush();
		  System.out.println(json);    
       }
	

}
