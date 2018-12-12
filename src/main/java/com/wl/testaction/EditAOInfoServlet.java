package com.wl.testaction;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EditAOInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    System.out.println("进入EditAOInfoServlet函数体了已经！！");
	    HttpSession httpSession = ((HttpServletRequest)request).getSession(true);
	    String AOSql =null;
	    
	    int pageNow = 1;
	    int pageLenth = 4;
	    int pageWill =1;
	    
	    
	    if (request.getParameter("pageLength")!=null) {
	    	pageLenth = Integer.parseInt(request.getParameter("pageLength"));
	    	httpSession.setAttribute("pageLenth", pageLenth);
		}
	    httpSession.setAttribute("pageLenth", pageLenth);
	    AOSql = "select PRODUCTID,AO_NO,ISSUE_NUM,AO_ORDER,ITEMID,AOVER,PARENTAO_NO,AO_TIME,AONAME,WORKPLACECODE,WORKPLACENAME,AO_CONTENT from  (select A.*,ROWNUM row_num " +
	    		"from (select * from aodetail) A where ROWNUM<="+(pageLenth*pageWill)+
	    		" order by AO_NO) where row_num>="+((pageWill-1)*pageLenth+1);

	    if (request.getParameter("will")!=null) {
			pageNow = Integer.parseInt((httpSession.getAttribute("pageNow")).toString()) ;
			pageLenth = Integer.parseInt((httpSession.getAttribute("pageLenth")).toString());
			int will = Integer.parseInt(request.getParameter("will"));
			
			if (will!=-3) {
				if (will==-1) {
					pageWill = pageNow -1;
				}
				if (will==-2) {
					pageWill =pageNow+1;
				}
				
				if (will==-4) {
					pageWill = 1;
				}
				AOSql = "select PRODUCTID,AO_NO,ISSUE_NUM,AO_ORDER,ITEMID,AOVER,PARENTAO_NO,AO_TIME,AONAME,WORKPLACECODE,WORKPLACENAME,AO_CONTENT from  (select A.*,ROWNUM row_num " +
	    		"from (select * from aodetail) A where ROWNUM<="+(pageLenth*pageWill)+
	    		" order by AO_NO) where row_num>="+((pageWill-1)*pageLenth+1);
			}else {
				AOSql = 
			    	"select PRODUCTID,AO_NO,ISSUE_NUM,AO_ORDER,ITEMID,AOVER,PARENTAO_NO,AO_TIME,AONAME,WORKPLACECODE,WORKPLACENAME,AO_CONTENT from (select A.*,ROWNUM row_num " +
			    	"from (select * from aodetail) A order by AO_NO desc) where row_num<="
			    	+pageLenth+" order by AO_NO ";
			
			}
			System.out.println(pageNow+"  "+will+"  "+pageLenth);
		}

	    System.out.println("AOSql=="+AOSql);

	    ResultSet machineworkerrs = null;
	    ResultSet machiners =null;
		try{
			machiners = Sqlhelper.executeQuery(AOSql, null);
			out.println("<AO>");
			
			while (machiners.next()) {
				out.println("<AOInfo>");
				out.println("<PRODUCTID>"+machiners.getString(1)+"</PRODUCTID>");
				out.println("<AO_NO>"+machiners.getString(2)+"</AO_NO>");
				out.println("<ISSUE_NUM>"+machiners.getString(3)+"</ISSUE_NUM>");
				out.println("<AO_ORDER>"+machiners.getString(4)+"</AO_ORDER>");
				out.println("<ITEMID>"+machiners.getString(5)+"</ITEMID>");
				out.println("<AOVER>"+machiners.getString(6)+"</AOVER>");
				out.println("<PARENTAO_NO>"+machiners.getString(7)+"</PARENTAO_NO>");
				out.println("<AO_TIME>"+machiners.getString(8)+"</AO_TIME>");
				out.println("<AONAME>"+machiners.getString(9)+"</AONAME>");
				out.println("<WORKPLACECODE>"+machiners.getString(10)+"</WORKPLACECODE>");
				out.println("<WORKPLACENAME>"+machiners.getString(11)+"</WORKPLACENAME>");				
				out.println("<AO_CONTENT>"+machiners.getString(12)+"</AO_CONTENT>");
				out.println("</AOInfo>");

			}
			out.println("</AO>");
			out.flush();
			out.close();
			
			httpSession.setAttribute("pageNow", pageWill);
			
		}catch(Exception e){
		}  finally{
			try {
				if(machiners!=null){
					machiners.close();
				}
				if(machineworkerrs!=null){
					machineworkerrs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}