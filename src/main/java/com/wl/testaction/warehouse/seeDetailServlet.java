package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkin;
import com.wl.forms.CheckinDetl;
import com.wl.tools.Sqlhelper;

public class seeDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
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


		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheet_id");
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
		String totalCountSql = "select count(*) from mycheckin_detl where checksheet_id='"+checksheet_id+"'";
		    try {
		    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
			} catch (Exception e) {
				e.printStackTrace();
	}
		
		String sql="select * from (select A.*,ROWNUM row_num from (select EM.* from mycheckin_detl EM where EM.checksheet_id='"+checksheet_id+"' order by checksheet_id asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by checksheet_id) B " +
				"where row_num>="+((pageNo-1)*countPerPage+1)+" order by checksheet_id";
		System.out.println(sql);
		List<CheckinDetl> resultList=new ArrayList<CheckinDetl>();
		try{
			
			resultList=Sqlhelper.exeQueryList(sql, null, CheckinDetl.class);	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
