package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPayDetl;
import com.wl.tools.Sqlhelper;

public class ShowPayDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowPayDetailServlet() {
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
		String paySheetid=request.getParameter("paySheetid");
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int totalCount=0;
		String totalCountSql="select count(*) from popaydetl where PAYSHEETID='"+paySheetid+"'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String paySql="select * from (select A.*,rownum row_num from (select EM.* from popaydetl EM where paysheetid='"+paySheetid+"' order by paysheetid) A " +
				"where rownum<="+(pageSize*pageNow)+" order by paysheetid) B where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<PoPayDetl> resultList=new ArrayList<PoPayDetl>();
		try{
			resultList=Sqlhelper.exeQueryList(paySql, null, PoPayDetl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
