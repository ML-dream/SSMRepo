package com.wl.testaction.partPlanManage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PluSoft.Utils.JSON;

import com.wl.tools.Sqlhelper;

public class DiscardSelectedGanttSaveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();		
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}		//取参数
		String s = JSON.Encode(buffer.toString());	//参数重新编码吗为json
		
		ArrayList totaldata = (ArrayList)Test.JSON.Decode(s);	//参数转换为list
	    	for(int i=0,l=totaldata.size(); i<l; i++){
		    	HashMap<Object,Object> datamap = (HashMap)totaldata.get(i);
		    	ArrayList<Object> list2 =  (ArrayList) datamap.get("Tasks");
		      for(int j=0;j<list2.size();j++){
		    	HashMap map2 = (HashMap) list2.get(j);	
		    	Date start = (Date)map2.get("Start");
		    	Date finish = (Date)map2.get("Finish");
		    	SimpleDateFormat formatter= new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		        String Start = formatter.format(start); 
		        String Finish =formatter.format(finish);
		        String  isCo=map2.get("isCo").toString();
		        String orderId=map2.get("orderId").toString();
		        String productId=map2.get("productId").toString();
		        String issueNum=map2.get("issueNum").toString();
		        String operId=map2.get("UID").toString();
		        String isDiscard=map2.get("isDiscard").toString();
                String Sql;
		      String sql ="update processesplan set planstarttime=to_date('"+Start+"','yyyy-mm-dd hh24:mi:ss'),"+
		      		      "planendtime=to_date('"+Finish+"','yyyy-mm-dd hh24:mi:ss'),"+
		    		      " isCo='"+isCo+"' " +
		      			  "where orderid=? and productId=? and issuenum=?  and operId=? and isDiscard=?";
		        if(isCo=="1"||isCo.equals("1")){
			         String workBranch="waix";
			          Sql="update fo_detail set is_Co='"+isCo+"',workbranch='"+workBranch+"' where product_Id=? and issue_Num=? and fo_no=? and foId=(select foId from foheader where orderId=? and productId=? and issueNum=? )";
			        }
		        else{
		        	  Sql="update fo_detail set is_Co='"+isCo+"' where product_Id=? and issue_Num=? and fo_no=? and foId=(select foId from foheader where orderId=? and productId=? and issueNum=? )";
		        }
		      String[] params={orderId,productId,issueNum,operId,isDiscard};
		      String[] Params={productId,issueNum,operId,orderId,productId,issueNum};
		     try {

		    	   Sqlhelper.executeUpdate(sql, params);
		    	   Sqlhelper.executeUpdate(Sql, Params);
				} catch (SQLException e) {
					String json = "{\"result\":\"操作失败!\"}";
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append(json).flush();
					e.printStackTrace();
				}  
		     
		      }
	    	}
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();   
	}

}
