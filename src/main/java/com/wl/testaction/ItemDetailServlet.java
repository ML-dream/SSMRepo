package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xpath.internal.operations.And;
import com.wl.tools.Sqlhelper;

import java.sql.*;
import cfmes.util.DealString;
import cfmes.util.sql_data;

public class ItemDetailServlet extends HttpServlet {
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入ItemDetailServlet函数体了已经！");
		try{
			request.setCharacterEncoding("utf-8"); 
	        response.setCharacterEncoding("utf-8");
		    response.setContentType("text/xml"); 
		    response.setHeader("Charset","utf-8");
			    
		    PrintWriter out = response.getWriter();

		    response.setHeader("Cache-Control","no-cache");
		    out.println("<?xml version='1.0' encoding='utf-8'?>");
		    out.println("<Item>");
		    /*************************以下是分页内容**************************/
		    int pageNow = 1;
		    int pageSize = 20;
		    int totalNum = 0;
		    
		    pageNow = request.getParameter("pageNow")!=null?Integer.parseInt(request.getParameter("pageNow")):pageNow;
		    pageSize = request.getParameter("pageSize")!=null?Integer.parseInt(request.getParameter("pageSize")):pageSize;
		    
		    if (request.getParameter("totalNum")==null) {
				//此处查询出中记录数
		    	String totalSql = "select count(1) from item";
		    	ResultSet rs = Sqlhelper.executeQuery(totalSql, null);
		    	try {
					if (rs.next()) {
						totalNum = rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					if (rs!=null) {
						try {
							rs.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					rs = null;
				}
			}else {
				totalNum = Integer.parseInt(request.getParameter("totalNum"));
			}
		    System.out.println(pageNow+" "+pageSize+" "+totalNum);
		    /*************************以上是分页内容**************************/
		    
    		String itemDetailSql = " select a.item_id,itt.item_typedesc,a.item_name" +
			    				"	from " +
			    				"		(select it.item_id,it.item_typeid,it.item_name,it.update_time,ROWNUM row_num" +
			    				"		 from item it" +
			    				"		 where ROWNUM<=" +pageNow*pageSize+
			    				"		 order by update_time desc" +
			    				"		) a" +
			    				"	left join itemtype itt" +
			    				"	on a.item_typeid = itt.item_typeid" +
			    				"	" +
			    				"	where row_num>=" +((pageNow-1)*pageSize+1)+
			    				"	order by update_time desc";
    		itemDetailSql = "select a.item_id,itt.item_typedesc,a.item_name " +
    						"from (select row_number() over(order by update_time desc ) num,item_id,item_typeid,item_name,update_time from item) a " +
    						"left join itemtype itt" +
		    				"	on a.item_typeid = itt.item_typeid" +
		    				"	" +
		    				"where num between "+ ((pageNow-1)*pageSize+1)+" and "+ pageNow*pageSize;
    		System.out.println(itemDetailSql);
    		ResultSet itemRs = null;
    		try {
    			itemRs = Sqlhelper.executeQuery(itemDetailSql, null);
    			while(itemRs.next()){
    				out.println("<item_list>");
	    			out.println("<item_id>"+itemRs.getString(1)+"</item_id>");
	    			out.println("<item_type>"+itemRs.getString(2)+"</item_type>");
	    			out.println("<item_name>"+itemRs.getString(3)+"</item_name>");
	    			out.println("</item_list>");
	    			System.out.println(itemRs.getString(1)+"  "+itemRs.getString(2)+"  "+itemRs.getString(3));
    			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(itemRs!=null){
					try {
						itemRs.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				itemRs=null;
			}
			out.println("<pageNow>"+pageNow+"</pageNow>");
			out.println("<pageSize>"+pageSize+"</pageSize>");
			out.println("<totalNum>"+totalNum+"</totalNum>");
			
			out.println("</Item>");				
			out.flush();
			out.close();
		
		}catch(Exception e){
			System.out.println("ItemDetailServlet出现异常："+e);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
         doPost(request,response);
    }
}
