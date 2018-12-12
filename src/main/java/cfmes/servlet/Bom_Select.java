package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.sql.*;
import cfmes.util.DealString;
import cfmes.util.sql_data;

public class Bom_Select extends HttpServlet {
//	sql_data sqlbean=new sql_data();
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();
		try{
			request.setCharacterEncoding("utf-8"); 
			//System.out.println("hello");
//		    HttpSession session = request.getSession();
//		    Tree_session tree_session=(Tree_session)session.getAttribute("tree_session");
		    
		    String type = ds.toString(ds.toGBK(request.getParameter("type")));
		    //String sid  = new String(request.getParameter("sid").getBytes("ISO8859_1"),"gbk");
		    String flight_type = ds.toString(ds.toGBK(request.getParameter("flight_type")));
		    
		    String product_id  = ds.toString(ds.toGBK(request.getParameter("product_id")));
		    String issue_num = ds.toString(ds.toGBK(request.getParameter("issue_num")));
		    String lot         = ds.toString(ds.toGBK(request.getParameter("lot")));
            //System.out.println("product_id:"+product_id);
//		       if(type.equals("flight_type")){tree_session.setFlight_type(sid);}
//		       if(type.equals("product_id")){tree_session.setProduct_id(sid);}
//		       if(type.equals("lot")){tree_session.setLot(sid);}
//		       if(type.equals("sortie")){
//		          String[] num = new String[4];
//		          num = sid.split("--");
//		          tree_session.setSortie(num[0]+"-"+num[1]);
//		          tree_session.setIssue_num(num[2]);
//		          tree_session.setIssue(num[3]); 
//		       }
		        response.setCharacterEncoding("utf-8");
			    response.setContentType("text/xml"); 
			    response.setHeader("Charset","utf-8");
			    
		    PrintWriter out = response.getWriter();

		    response.setHeader("Cache-Control","no-cache");
		    out.println("<?xml version='1.0' encoding='utf-8'?>");
		    out.println("<Item>");
		    if(type.equals("flight_type")){
		    	//System.out.println("hello2");	
		    	 ResultSet rs_product_id =sqlbean.executeQuery( "Select distinct i.product_id,i2.item_name"
		    			 +" FROM work.issuedeploy i,work.item i2 WHERE i.product_type ='"+flight_type+"' and i2.item_id=i.product_id"
		    			 +" and i.product_id in(select distinct product_id from work.ebom) order by i.product_id");
		    	  while(rs_product_id.next()){
		    	    out.println("<product_id>");
		    	    out.println("<Name_product_id>"+rs_product_id.getString("product_id")+"��"+rs_product_id.getString("item_name")+"��</Name_product_id>");
		    	    out.println("<ID_product_id>"+rs_product_id.getString("product_id")+ "</ID_product_id>");
		    	    out.println("</product_id>");
		    	  }rs_product_id.close();
//		    	  sqlbean.closeStmt();
		    	  sqlbean.closeConn();
		    	}
		    if(type.equals("product_id")){
		    	 ResultSet rs_issuenum =sqlbean.executeQuery( "Select distinct issue_num FROM work.issuedeploy WHERE product_type ='"+flight_type
		    	                                       +"' and product_id ='"+product_id+"'  order by issue_num");
		    	while(rs_issuenum.next()){
		    	    out.println("<issuenum>");
		    	    out.println("<Name_issuenum>"+rs_issuenum.getString("issue_num")+ "</Name_issuenum>");
		    	    out.println("<ID_issuenum>"+rs_issuenum.getString("issue_num")+ "</ID_issuenum>");
		    	    out.println("</issuenum>");
		    	}rs_issuenum.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    if(type.equals("issue_num")){
		    	 ResultSet rs_lot =sqlbean.executeQuery( "Select distinct lot FROM work.issuedeploy WHERE product_type ='"+flight_type
		    	                                       +"' and product_id ='"+product_id+"' and issue_num='"+issue_num+"'  order by lot");
		    	while(rs_lot.next()){
		    	    out.println("<lot>");
		    	    out.println("<Name_lot>"+rs_lot.getString("lot")+ "</Name_lot>");
		    	    out.println("<ID_lot>"+rs_lot.getString("lot")+ "</ID_lot>");
		    	    out.println("</lot>");
		    	}rs_lot.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		    }
		    if(type.equals("lot")){
		    	ResultSet rs_sortie =sqlbean.executeQuery( "Select b_sortie,e_sortie,issue_num,memo FROM work.issuedeploy WHERE product_type ='"+flight_type
		    	                        +"' and product_id ='"+product_id+"' and issue_num='"+issue_num+"' and lot ='"+lot+"' order by b_sortie");
		    	while(rs_sortie.next()){
		    	    out.println("<sortie>");
		    	    out.println("<Name_sortie>"+rs_sortie.getString("b_sortie")+"-"+rs_sortie.getString("e_sortie")+"</Name_sortie>");
		    	    out.println("<ID_sortie>"+rs_sortie.getString("b_sortie")+"---"+rs_sortie.getString("e_sortie")+"---"+rs_sortie.getString("issue_num")+"---"+rs_sortie.getString("memo")+"</ID_sortie>");
		    	    out.println("</sortie>");
		    	}
		    	rs_sortie.close();
//		    	sqlbean.closeStmt();
		    	sqlbean.closeConn();
		     }
		    if(type.equals("flight_type2")){
		    	 ResultSet rs_product_id =sqlbean.executeQuery( "Select distinct product_id FROM meteor.order_detail WHERE flight_type ='"+flight_type+"'  order by product_id");
		    	  while(rs_product_id.next()){
		    	    out.println("<product_id2>");
		    	    out.println("<Name_product_id2>"+rs_product_id.getString("product_id")+"</Name_product_id2>");
		    	    out.println("<ID_product_id2>"+rs_product_id.getString("product_id")+ "</ID_product_id2>");
		    	    out.println("</product_id2>");
		    	  }rs_product_id.close();
//		    	  sqlbean.closeStmt();
		    	  sqlbean.closeConn();
		    	}
		    if(type.equals("product_id2")){
//		    	  String[] num = new String[2];
//		          num = sid.split("--");
//		          String flight_type = num[1];
//		          String product_id = num[0];
		          //System.out.println(flight_type+" "+product_id);
		    	 ResultSet rs_lot =sqlbean.executeQuery( "Select distinct lot FROM meteor.order_detail WHERE flight_type ='"+flight_type+"' and product_id ='"+product_id+"' order by lot");
		    	while(rs_lot.next()){
		    	    out.println("<lot2>");
		    	    out.println("<Name_lot2>"+rs_lot.getString("lot")+ "</Name_lot2>");
		    	    out.println("<ID_lot2>"+rs_lot.getString("lot")+ "</ID_lot2>");
		    	    out.println("</lot2>");
		    	}rs_lot.close();
		    	sqlbean.closeConn();
		    	
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
