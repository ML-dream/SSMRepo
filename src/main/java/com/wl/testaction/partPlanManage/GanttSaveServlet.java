package com.wl.testaction.partPlanManage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import JSOM.DB;
import JSOM.FandT;
import JSOM.JackSonTrans;
import PluSoft.Utils.JSON;

public class GanttSaveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		Object data=request.getSession().getAttribute("willSeeParts");
	    JSONArray jsonArray = JSONArray.fromObject(data);
		List<Order> orders = JSONArray.toList(jsonArray, Order.class);
	    Order order = orders.get(0);
		
		BufferedReader reader = request.getReader();		
		StringBuffer buffer = new StringBuffer();
		String string;
		while ((string = reader.readLine()) != null) {
			buffer.append(string);
		}		//取参数
		String s = JSON.Encode(buffer.toString());	//参数重新编码吗为json
		
		ArrayList totaldata = (ArrayList)Test.JSON.Decode(s);	//参数转换为list
		String json="";
//	     System.out.println(totaldata.get(0));
	    	for(int i=0,l=totaldata.size(); i<l; i++){
		    	HashMap<Object,Object> datamap = (HashMap)totaldata.get(i);
		    	String operId=datamap.get("UID").toString();
		    	ArrayList<Object> list2 =  (ArrayList) datamap.get("Tasks"); 
//		    	System.out.println(list2.get(0));	
		    	HashMap map2 = (HashMap) list2.get(0);	
		    	Date start = (Date)map2.get("Start");
		    	Date finish = (Date)map2.get("Finish");
		    	SimpleDateFormat formatter= new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		        String Start = formatter.format(start); 
		        String Finish =formatter.format(finish);
		        String  isCo="0"/*map2.get("isCo").toString()*/;
                String Sql;
//	    		System.out.println(start);
		      String sql ="update processesplan set planstarttime=to_date('"+Start+"','yyyy-mm-dd hh24:mi:ss'),"+
		      		      "planendtime=to_date('"+Finish+"','yyyy-mm-dd hh24:mi:ss'),"+
		    		      " isCo='"+isCo+"' " +
		      			  "where orderid=? and productId=? and issuenum=? and drawingid=? and operId=?";
		        if(isCo=="1"||isCo.equals("1")){
			         String workBranch="waix";
			          Sql="update fo_detail set is_Co='"+isCo+"',workbranch='"+workBranch+"' where product_Id=? and issue_Num=? and fo_no=? and foId=(select foId from foheader where orderId=? and productId=? and issueNum=? and drawingId=?)";
			        }
		        else{
		        	  Sql="update fo_detail set is_Co='"+isCo+"' where product_Id=? and issue_Num=? and fo_no=? and foId=(select foId from foheader where orderId=? and productId=? and issueNum=? and drawingId=?)";
		        }
		      String[] params={order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId(),operId};
		      String[] Params={order.getProductId(),order.getIssueNum(),operId,order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId()};
		     
		      try {

		    	   Sqlhelper.executeUpdate(sql, params);

		    	   Sqlhelper.executeUpdate(Sql, Params);
		    	    json = "{\"result\":\"操作成功!\"}";
				} catch (SQLException e) {
					 json = "{\"result\":\"操作失败!\"}";
//					response.setCharacterEncoding("UTF-8");
//					response.getWriter().append(json).flush();
					e.printStackTrace();
				}  
		      

	    	}
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();   	 

	}

}
