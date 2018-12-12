package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import cfmes.util.DealString;
import cfmes.util.sql_data;

public class AoDispatch_select extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入AoDispatch_select函数体了已经！");
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		try{
			request.setCharacterEncoding("utf-8"); 			   
		    String type = request.getParameter("type");
		    String orderid = request.getParameter("orderid");
		    String item_id = request.getParameter("item_id");		    

	        response.setCharacterEncoding("utf-8");
		    response.setContentType("text/xml"); 
		    response.setHeader("Charset","utf-8");
			    
		    PrintWriter out = response.getWriter();

		    response.setHeader("Cache-Control","no-cache");
		    out.println("<?xml version='1.0' encoding='utf-8'?>");
		    out.println("<Item>");

	
		    if(type.equals("orderid")){
		    	String item_id_sql ="Select distinct product_id FROM work.ao_plan WHERE order_id ='"+orderid
		    	 +"' order by product_id" ;
		    	System.out.println("item_id_sql=="+item_id_sql);
		    	
		    	 ResultSet rs_item_id =sqlbean.executeQuery(item_id_sql);
		    	 while(rs_item_id.next()){
			    	    out.println("<item_id>");
			    	    out.println("<Name_item_id>"+rs_item_id.getString("product_id")+ "</Name_item_id>");
			    	    out.println("<ID_item_id>"+rs_item_id.getString("product_id")+ "</ID_item_id>");
			    	    out.println("</item_id>");
			    	}rs_item_id.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }

		    if(type.equals("item_id")){
		    	String oper_id_sql = "Select distinct ao_no FROM work.station_plan WHERE product_id='"+item_id+"' order by ao_no";
		    	System.out.println("oper_id_sql=="+oper_id_sql);
		    	
		    	ResultSet rs_oper_id =sqlbean.executeQuery(oper_id_sql);
		    	while(rs_oper_id.next()){
		    	    out.println("<oper_id>");
		    	    out.println("<Name_oper_id>"+rs_oper_id.getString("ao_no")+ "</Name_oper_id>");
		    	    out.println("<ID_oper_id>"+rs_oper_id.getString("ao_no")+ "</ID_oper_id>");
		    	    out.println("</oper_id>");
		    	}rs_oper_id.close();
		    	sqlbean.closeConn();
		    }
		    if(type.equals("worker")){
		    	String machineid_sql = "Select distinct place_id,place_name FROM work.workplace order by place_id";
		    	System.out.println("machineid_sql=="+machineid_sql);
		    	
		    	ResultSet rs_machineid =sqlbean.executeQuery(machineid_sql);
		    	while(rs_machineid.next()){
		    	    out.println("<machineid>");
		    	    out.println("<Name_machineid>"
		    	    		+rs_machineid.getString("place_id")+"("
		    	    		+rs_machineid.getString("place_name")+")</Name_machineid>");
		    	    out.println("<ID_machineid>"
		    	    		+rs_machineid.getString("place_id")+ "</ID_machineid>");
		    	    out.println("</machineid>");
		    	}rs_machineid.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    
		    if(type.equals("oper_id")){
		    	String workerid_sql = "Select distinct staff_code,staff_name FROM work.employee_info order by staff_code";
		    	System.out.println("workerid_sql=="+workerid_sql);
		    	
		    	ResultSet rs_machineid =sqlbean.executeQuery(workerid_sql);
		    	while(rs_machineid.next()){
		    	    out.println("<workerid>");
		    	    out.println("<Name_workerid>"
		    	    		+rs_machineid.getString("staff_code")+"("
		    	    		+rs_machineid.getString("staff_name")+")</Name_workerid>");
		    	    out.println("<ID_workerid>"
		    	    		+rs_machineid.getString("staff_code")+ "</ID_workerid>");
		    	    out.println("</workerid>");
		    	}rs_machineid.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    
		    if (type.equals("item_id") || type.equals("machineid")) {
		    	String alreadyDispatchedsql = 
	    		"select ORDER_ID,PRODUCT_ID,AO_NO,PLACE_ID,START_TIME,END_TIME,DISPATCH_PERSON,DISPATCH_TIME,worker_id" +
	    		" from aodispatch where ORDER_ID='"+orderid+"' and PRODUCT_ID = '"+item_id+"'order by AO_NO";
	    		System.out.println("alreadyDispatchedsql==="+alreadyDispatchedsql);
	    		
	    		ResultSet alreadyDispatchedrs = sqlbean.executeQuery(alreadyDispatchedsql);
	    		int i=0;
	    		while (alreadyDispatchedrs.next()) {
//	    			out.println("<alreadyDispatched>");
	    			
	    			System.out.println(++i);
	    			System.out.println(alreadyDispatchedrs.getString(1));
	    			out.println("<al_lists>");
	    			out.println("<al_orderid>"+alreadyDispatchedrs.getString(1)+"</al_orderid>");
	    			out.println("<al_item_id>"+alreadyDispatchedrs.getString(2)+"</al_item_id>");
	    			out.println("<al_oper_id>"+alreadyDispatchedrs.getString(3)+"</al_oper_id>");
	    			out.println("<al_machine_id>"+alreadyDispatchedrs.getString(4)+"</al_machine_id>");
	    			out.println("<al_start_time>"+alreadyDispatchedrs.getString(5)+"</al_start_time>");
	    			out.println("<al_end_time>"+alreadyDispatchedrs.getString(6)+"</al_end_time>");
	    			out.println("<al_dispatch_person>"+alreadyDispatchedrs.getString(7)+"</al_dispatch_person>");
	    			out.println("<al_dispatch_time>"+alreadyDispatchedrs.getString(8)+"</al_dispatch_time>");
	    			out.println("<al_worker>"+alreadyDispatchedrs.getString(8)+"</al_worker>");
	    			out.println("</al_lists>");
//	    			out.println("</alreadyDispatched>");
	    		}
	    		alreadyDispatchedrs.close();
			}

		out.println("</Item>");				
		out.flush();
		out.close();
		
		}catch(Exception e){
			System.out.println("Bom_Select����ʱ���?����Ϊ��"+e);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
         doPost(request,response);
    }
}
