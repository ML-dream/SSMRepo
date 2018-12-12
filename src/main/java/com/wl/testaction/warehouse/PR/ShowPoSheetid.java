package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Poplan;
import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class ShowPoSheetid extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowPoSheetid() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
		String totalCountSql="select count(*) from po_plan";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String poSql="select PO_SHEETID,to_char(POSTART_DATE,'yyyy-MM-dd') postartdate,CUSTOMERID,C.COMPANYNAME customerName," +
				"B.CONNECTOR,B.CONNECTORTEL,to_char(END_DATE,'yyyy-MM-dd') enddate from (select A.*,rownum from (select EM.* from po_plan EM order by po_sheetid ) A " +
				"where rownum<="+(pageNow*pageSize)+" order by po_sheetid) B " +
				"left join supplier C on C.companyid=B.customerid " +
				"where rownum>="+(pageSize*(pageNow-1))+"";
		List<Poplan> resultList=new ArrayList<Poplan>();
		try{
			resultList=Sqlhelper.exeQueryList(poSql, null, Poplan.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
	}

}
