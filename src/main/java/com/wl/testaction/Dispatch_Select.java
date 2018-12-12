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

public class Dispatch_Select extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		try{
			request.setCharacterEncoding("utf-8"); 			   
		    String type = ds.toString(ds.toGBK(request.getParameter("type")));
		    String orderid = ds.toString(ds.toGBK(request.getParameter("orderid")));
		    String issue_num = ds.toString(ds.toGBK(request.getParameter("issue_num")));
		    String item_id = ds.toString(ds.toGBK(request.getParameter("item_id")));
		    String start_time = request.getParameter("start_time");
		    String end_time = request.getParameter("end_time");
//		    String oper_id = ds.toString(ds.toGBK(request.getParameter("oper_id")));
//		    String machineid = ds.toString(ds.toGBK(request.getParameter("machineid")));
			
	        response.setCharacterEncoding("utf-8");
		    response.setContentType("text/xml"); 
		    response.setHeader("Charset","utf-8");
			    
		    PrintWriter out = response.getWriter();

		    response.setHeader("Cache-Control","no-cache");
		    out.println("<?xml version='1.0' encoding='utf-8'?>");
		    out.println("<Item>");
		    if (type.equals("end_time")) {
				String order_sql = 
				"select distinct order_id from work.partplan " +
				"where (plan_bgtime>'"+start_time+"' and plan_bgtime<'"+end_time+"') " +
				"or (plan_edtime>'"+start_time+"' and plan_edtime<'"+end_time+"')";
				System.out.println("order_sql=="+order_sql);
				
				ResultSet rs_order =sqlbean.executeQuery(order_sql);
				while (rs_order.next()) {
					out.println("<order>");
					out.println("<order_id>"+rs_order.getString(1)+"</order_id>");
					out.println("</order>");
				}
		    }
		    
		    
		    
		    if(type.equals("orderid")){
		    	
		    	String issue_num_sql = "Select distinct issue_num"
	    			 +" FROM work.partplan where order_id='"+orderid+"'";
		    	System.out.println("issue_num_sql=="+issue_num_sql);
		    	
		    	ResultSet rs_issue_num =sqlbean.executeQuery(issue_num_sql);
		    	 
		    	while(rs_issue_num.next()){
		    	    out.println("<issue_num>");
		    	    out.println("<Name_issue_num>"
	    	    		+rs_issue_num.getString("issue_num")+ "</Name_issue_num>");
		    	    out.println("<ID_issue_num>"
	    	    		+rs_issue_num.getString("issue_num")+ "</ID_issue_num>");
		    	    out.println("</issue_num>");
		    	}rs_issue_num.close();
//		    	  sqlbean.closeStmt();
	    	    sqlbean.closeConn();
		    }
		    /*
		    if(type.equals("equipment_drawid")){
		    	 ResultSet rs_issue_num =sqlbean.executeQuery
		    	 	( "Select distinct issue_num FROM work.DEPARTURESHEET WHERE orderid ='"
		    			 +orderid+"' and equipment_drawid ='"
		    			 +equipment_drawid+"'  order by issue_num");
		    	while(rs_issue_num.next()){
		    	    out.println("<issue_num>");
		    	    out.println("<Name_issue_num>"
		    	    		+rs_issue_num.getString("issue_num")+ "</Name_issue_num>");
		    	    out.println("<ID_issue_num>"
		    	    		+rs_issue_num.getString("issue_num")+ "</ID_issue_num>");
		    	    out.println("</issue_num>");
		    	}rs_issue_num.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    */	
		    if(type.equals("issue_num")){
		    	String item_id_sql ="Select distinct item_id FROM work.partplan WHERE order_id ='"+orderid
		    	 +"' and issue_num ='"+issue_num+"'order by item_id" ;
		    	System.out.println("item_id_sql=="+item_id_sql);
		    	
		    	 ResultSet rs_item_id =sqlbean.executeQuery(item_id_sql);
		    	 while(rs_item_id.next()){
			    	    out.println("<item_id>");
			    	    out.println("<Name_item_id>"+rs_item_id.getString("item_id")+ "</Name_item_id>");
			    	    out.println("<ID_item_id>"+rs_item_id.getString("item_id")+ "</ID_item_id>");
			    	    out.println("</item_id>");
			    	}rs_item_id.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    /*
		    if(type.equals("product_id")){
		    	 ResultSet rs_item_id =sqlbean.executeQuery
		    	 	( "Select distinct item_id FROM work.DEPARTURESHEET WHERE orderid ='"+orderid
		    	 		+"' and equipment_drawid ='"+equipment_drawid+"' and issue_num='"+issue_num
		    	 			+"'and product_id='"+product_id+"' order by item_id");
		    	while(rs_item_id.next()){
		    	    out.println("<item_id>");
		    	    out.println("<Name_item_id>"+rs_item_id.getString("item_id")+ "</Name_item_id>");
		    	    out.println("<ID_item_id>"+rs_item_id.getString("item_id")+ "</ID_item_id>");
		    	    out.println("</item_id>");
		    	}rs_item_id.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    */
		    if(type.equals("item_id")){
		    	String oper_id_sql = "Select distinct a.oper_id,b.fo_opname FROM work.process_plan a,work.fo_detail b WHERE a.item_id='"+item_id+"' and b.item_id='"+item_id+"' and b.fo_operid=a.oper_id order by oper_id";
		    	System.out.println("oper_id_sql=="+oper_id_sql);

		    	ResultSet rs_oper_id =sqlbean.executeQuery(oper_id_sql);

		    	while(rs_oper_id.next()){
		    	    out.println("<oper_id>");
		    	    out.println("<Name_oper_id>"+rs_oper_id.getString(1)+ "("+rs_oper_id.getString(2)+")</Name_oper_id>");
		    	    out.println("<ID_oper_id>"+rs_oper_id.getString(1)+ "</ID_oper_id>");
		    	    out.println("</oper_id>");
		    	}rs_oper_id.close();

//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    
		    if(type.equals("oper_id")){
		    	String workerid_sql = "select * from work.employee_info";
		    	System.out.println("workerid_sql=="+workerid_sql);
		    	
		    	ResultSet rs_workerid =sqlbean.executeQuery(workerid_sql);
		    	while(rs_workerid.next()){
		    	    out.println("<workerid>");
		    	    out.println("<Name_workerid>"
		    	    		+rs_workerid.getString("staff_code")+"("
		    	    		+rs_workerid.getString("staff_name")+")("
		    	    		+rs_workerid.getString("dept_id")+")</Name_workerid>");
		    	    out.println("<ID_workerid>"
		    	    		+rs_workerid.getString("staff_code")+ "</ID_workerid>");
		    	    out.println("</workerid>");
		    	    System.out.println(rs_workerid.getString("staff_code"));
		    	    System.out.println(rs_workerid.getString("staff_name"));
		    	    System.out.println(rs_workerid.getString("dept_id"));
		    	}rs_workerid.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    
		    
		    
		    
		    if(type.equals("workerid")){
		    	String machineid_sql = "Select distinct machineid,machinename,machinespec FROM work.machinfo order by machineid";
		    	System.out.println("machineid_sql=="+machineid_sql);
		    	
		    	ResultSet rs_machineid =sqlbean.executeQuery(machineid_sql);
		    	while(rs_machineid.next()){
		    	    out.println("<machineid>");
		    	    out.println("<Name_machineid>"
		    	    		+rs_machineid.getString("machineid")+"("
		    	    		+rs_machineid.getString("machinename")+")("
		    	    		+rs_machineid.getString("machinespec")+")</Name_machineid>");
		    	    out.println("<ID_machineid>"
		    	    		+rs_machineid.getString("machineid")+ "</ID_machineid>");
		    	    out.println("</machineid>");
		    	}rs_machineid.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    
		    if (type.equals("item_id") || type.equals("machineid")) {
		    	String alreadyDispatchedsql = 
	    		"select orderid,issue_num,item_id,oper_id,machine_id,start_time,end_time,worker from departuresheet " +
	    		"where orderid='"+orderid+"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+"'order by oper_id";
	    		System.out.println("alreadyDispatchedsql==="+alreadyDispatchedsql);
	    		
	    		ResultSet alreadyDispatchedrs = sqlbean.executeQuery(alreadyDispatchedsql);
	    		int i=0;
	    		while (alreadyDispatchedrs.next()) {
//	    			out.println("<alreadyDispatched>");
	    			
	    			System.out.println(++i);
	    			System.out.println(alreadyDispatchedrs.getString(1));
	    			out.println("<al_lists>");
	    			out.println("<al_orderid>"+alreadyDispatchedrs.getString(1)+"</al_orderid>");
	    			out.println("<al_issue_num>"+alreadyDispatchedrs.getString(2)+"</al_issue_num>");
	    			out.println("<al_item_id>"+alreadyDispatchedrs.getString(3)+"</al_item_id>");
	    			out.println("<al_oper_id>"+alreadyDispatchedrs.getString(4)+"</al_oper_id>");
	    			out.println("<al_machine_id>"+alreadyDispatchedrs.getString(5)+"</al_machine_id>");
	    			out.println("<al_start_time>"+alreadyDispatchedrs.getString(6)+"</al_start_time>");
	    			out.println("<al_end_time>"+alreadyDispatchedrs.getString(7)+"</al_end_time>");
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
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
         doPost(request,response);
    }
}
