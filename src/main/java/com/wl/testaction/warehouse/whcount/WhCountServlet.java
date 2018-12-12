package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.WhCountDetail;
import com.wl.tools.Sqlhelper;

public class WhCountServlet extends HttpServlet {


	public WhCountServlet() {
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
		String countSheetid=request.getParameter("countSheetid");
		String totalCountSql="select count(*) from whcountdetl where countSheetid='"+countSheetid+"'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String whcountSql="select * from (select A.*,rownum row_num from (select EM.* from whcountdetl EM where countSheetid='"+countSheetid+"' order by countSheetid asc) A where rownum<='"+(pageNow*pageSize)+"' " +
				"order by countSheetid) B where row_num>='"+(pageSize*(pageNow-1)+1)+"' order by countSheetid";
		List<WhCountDetail> resultList=new ArrayList<WhCountDetail>();
		try{
			resultList=Sqlhelper.exeQueryList(whcountSql, null, WhCountDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
	}

}
