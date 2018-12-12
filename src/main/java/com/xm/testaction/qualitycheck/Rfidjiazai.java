package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Rfidparam;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class Rfidjiazai extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String param = "";
	    param = request.getParameter("param");
	    //int param =Integer.parseInt(request.getParameter("param"));
	    String rfidcode="";
	    rfidcode = request.getParameter("barcode");
	    System.out.print(param);
	    /*String orderStr = "COMPANYID";*/
/*	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");*/
	   // pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	   // countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	  
	   /* String customname=StringUtil.isNullOrEmpty(request.getParameter("companyname"))?"":request.getParameter("companyname");*/
	    
	    /*HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    */
	    if(param.equals("1")){
	    	String totalCountSql = "select count(*) from RFIDBADCODE";
		    try {
				totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    String rfidSql= "";
		    rfidSql = "select IP,RFIDCODE FROM  RFIDBADCODE";
		    	
		   /* "select D.* from (SELECT ROWNUM ROW_NUM, C.* from RFIDBADCODE) D WHERE ROW_NUM" +
		    		" BETWEEN "+(countPerPage*(pageNo-1)+1)+" AND "+(countPerPage*pageNo)+"";*/
		     
		    List<Rfidparam> rfidList = new ArrayList<Rfidparam>();
		    try {
				rfidList = Sqlhelper.exeQueryList(rfidSql, null, Rfidparam.class);  
			} catch (Exception e) {
				e.printStackTrace();
			}
		    String json = PluSoft.Utils.JSON.Encode(rfidList);
		    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);
	    }
	    else if (param.equals("2")){
	
		    String rfidSql= "";
		    rfidSql = "delete FROM  RFIDBADCODE where RFIDCODE="+"'"+rfidcode+"'";
		    try {
				Sqlhelper.executeUpdate_noclose(rfidSql, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	
		   /* "select D.* from (SELECT ROWNUM ROW_NUM, C.* from RFIDBADCODE) D WHERE ROW_NUM" +
		    		" BETWEEN "+(countPerPage*(pageNo-1)+1)+" AND "+(countPerPage*pageNo)+"";*/
		     
		   /* List<Rfidparam> rfidList = new ArrayList<Rfidparam>();
		    try {
				rfidList = Sqlhelper.exeQueryList(rfidSql, null, Rfidparam.class);  
			} catch (Exception e) {
				e.printStackTrace();
			}
		    String json = PluSoft.Utils.JSON.Encode(rfidList);
		    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);*/
	    	
	    	
	    }
	    
	    else {
	    	System.out.println("无效参数");
	    }
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
