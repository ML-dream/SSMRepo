package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class ShowPrSheetid extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowPrSheetid() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String totalCountSql="select count(*) from pr";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String prSql="select PRSHEETID,to_char(PRDATE,'yyyy-MM-dd') prDate,CUSTOMERID,C.COMPANYNAME customerName," +
				"B.CONNECTOR,B.CONNECTORTEL,ISBILL from (select A.*,rownum from (select EM.* from pr EM order by prsheetid ) A " +
				"where rownum<="+(pageNow*pageSize)+" order by prsheetid) B " +
				"left join supplier C on C.companyid=B.customerid " +
				"where rownum>="+(pageSize*(pageNow-1))+"";
		List<pr> resultList=new ArrayList<pr>();
		try{
			resultList=Sqlhelper.exeQueryList(prSql, null, pr.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
	}

}
