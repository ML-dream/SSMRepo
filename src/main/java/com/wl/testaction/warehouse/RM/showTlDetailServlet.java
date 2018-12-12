package com.wl.testaction.warehouse.RM;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.LlSheetDetail;
import com.wl.forms.TlSheetDetail;
import com.wl.tools.Sqlhelper;

public class showTlDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=0;
		int pageSize=20;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String rmSheetid=request.getParameter("rmSheetid");
		String totalCountSql="select count(*) from tuiliao where rmSheetid='"+rmSheetid+"'";
		System.out.println("totalCountSql="+totalCountSql);
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="select * from (select A.*,rownum row_num from (select EM.* from tuiliao_detl EM where rmSheetid='"+rmSheetid+"' order by rmsheetid asc) A " +
				"where rownum<="+(pageSize*pageNow)+" order by rmsheetid) B where row_num>="+(pageSize*(pageNow-1))+"";	
		List<TlSheetDetail> resultList=new ArrayList<TlSheetDetail>();
		System.out.println("sql="+sql);
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, TlSheetDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
	}

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
		doGet(request,response);
	}

}
