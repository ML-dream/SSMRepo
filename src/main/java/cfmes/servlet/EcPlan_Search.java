package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EcPlan_Search extends HttpServlet {

	/**
	 * Constructor of the object.
	 */


	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sql ;
		sql_data sqlbean=new sql_data();
		DealString ds = new DealString();

		    String product_type = ds.toString(ds.toGBK(request.getParameter("product_type")));  
		    String product_id   = ds.toString(ds.toGBK(request.getParameter("product_id")));
		    String issue_num    = ds.toString(ds.toGBK(request.getParameter("issue_num")));
		    String lot          = ds.toString(ds.toGBK(request.getParameter("lot")));
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml"); 
		response.setHeader("Charset","utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    out.println("<ECPLAN>");
	    
		try{
			sql ="Select * FROM work.ec_plan  WHERE product_type ='"+product_type+"' and product_id='"+product_id+"' and issue_num ='"+issue_num+"' and lot='"+lot+"'" ;
		 ResultSet rs_ecebomdata =sqlbean.executeQuery(sql);
    	  while(rs_ecebomdata.next()){
    		    out.println("<ECPLANDATA>");
    		    out.println("<ORDER_ID>"+rs_ecebomdata.getString("ORDER_id")+"</ORDER_ID>");
    		    out.println("<PLAN_ID>"+rs_ecebomdata.getString("PLAN_id")+"</PLAN_ID>");
    		    out.println("<ITEM_ID>"+rs_ecebomdata.getString("item_id")+"</ITEM_ID>");
    			out.println("<F_ITEM_ID>"+rs_ecebomdata.getString("father_item_id")+"</F_ITEM_ID>");
    			out.println("<QUALITY_ID>"+rs_ecebomdata.getString("QUALITY_id")+"</QUALITY_ID>");
    			out.println("<STARTTIME>"+rs_ecebomdata.getString("Start_time")+"</STARTTIME>");
    			out.println("<ENDTIME>"+rs_ecebomdata.getString("end_time")+"</ENDTIME>");
    			out.println("<NUM>"+rs_ecebomdata.getString("NUM")+"</NUM>");
    			out.println("<ECTYPE>"+rs_ecebomdata.getString("ec_type")+"</ECTYPE>");
    			out.println("<ECCON>"+rs_ecebomdata.getString("ec_con")+"</ECCON>");
    			out.println("<ECTIME>"+rs_ecebomdata.getString("ec_time")+"</ECTIME>");
    			
    			out.println("<PRODUCT_TYPE>"+rs_ecebomdata.getString("product_type")+"</PRODUCT_TYPE>");
    			out.println("<PRODUCT_ID>"+rs_ecebomdata.getString("product_id")+"</PRODUCT_ID>");
    			out.println("<ISSUE_NUM>"+rs_ecebomdata.getString("issue_num")+"</ISSUE_NUM>");
    			out.println("<LOT>"+rs_ecebomdata.getString("lot")+"</LOT>");
    			
    			out.println("</ECPLANDATA>");
    	  }rs_ecebomdata.close();
    	  sqlbean.closeConn();
		
    	  out.println("</ECPLAN>");  
		out.flush();
		out.close();
	}catch(Exception e){
		System.out.println("EcPlan_Search����ʱ���?����Ϊ��"+e);
	}
	}

}
