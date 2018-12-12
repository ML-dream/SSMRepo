package com.wl.testaction;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class GoDispatchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    System.out.println("进入gojianceServlet函数体了已经！！！");

	    String orderId  = request.getParameter("orderId");
	    String flag = request.getParameter("flag");
	    String itemId = request.getParameter("itemId");
	    String productId = request.getParameter("productId");
	    String operId = request.getParameter("operId");
	  	System.out.println("==================================================="+orderId);
		
	  	
	  	String timeSql = "select start_time,end_time from process_plan where item_id='"+itemId+"' and oper_id='"+operId+"'";
	  	System.out.println(timeSql);
	  	ResultSet rs = null;
	  	try {
	  		rs = Sqlhelper.executeQuery(timeSql, null);
	  		if (rs.next()) {
				String startTime = rs.getString(1);
				String endTime = rs.getString(2);
				request.setAttribute("startTime", startTime);
				request.setAttribute("endTime", endTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					rs = null;
				}
			}
		}
		
	  	String workerSql = "select staff_code,staff_name,dept_id from work.employee_info";
	  	System.out.println(workerSql);
	  	
	  	ResultSet rs_workerid = null;
	  	try {
	  		rs_workerid = Sqlhelper.executeQuery(workerSql, null);

    	    List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		  	while(rs_workerid.next()){
	    	    Map<String, String> map = new HashMap<String,String>();
	    	    map.put("staff_code", rs_workerid.getString("staff_code"));
	    	    map.put("staff_name", rs_workerid.getString("staff_name"));
	    	    map.put("dept_id", rs_workerid.getString("dept_id"));
	    	    list.add(map);
	    	    
	    	    System.out.println(rs_workerid.getString("staff_code"));
	    	    System.out.println(rs_workerid.getString("staff_name"));
	    	    System.out.println(rs_workerid.getString("dept_id"));
	    	}
		  	request.setAttribute("worker", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	
	  	
	  	
		String machineid_sql = "Select distinct machineid,machinename,machinespec FROM work.machinfo order by machineid";
    	System.out.println("machineid_sql=="+machineid_sql);
    	
    	ResultSet rs_machineid =null;
	  	try {
	  		rs_machineid =Sqlhelper.executeQuery(machineid_sql, null);

    	    List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		  	while(rs_machineid.next()){
	    	    Map<String, String> map = new HashMap<String,String>();
	    	    map.put("machineid", rs_machineid.getString("machineid"));
	    	    map.put("machinename", rs_machineid.getString("machinename"));
	    	    map.put("machinespec", rs_machineid.getString("machinespec"));
	    	    list.add(map);
	    	    
	    	    System.out.println(rs_machineid.getString("machineid"));
	    	    System.out.println(rs_machineid.getString("machinename"));
	    	    System.out.println(rs_machineid.getString("machinespec"));
	    	}
		  	request.setAttribute("machine", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	request.setAttribute("productId", productId);
	  	request.setAttribute("operId", operId);
	  	request.setAttribute("orderId", orderId);
	  	request.setAttribute("flag", flag);
	  	request.setAttribute("itemId", itemId);
		this.getServletConfig().getServletContext().getRequestDispatcher("/dispatch/DoDispatch.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}


}
