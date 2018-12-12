package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProductCheckin;
import com.wl.tools.Sqlhelper;

public class showProductCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showProductCheckinServlet() {
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
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String totalCountSql="";
		String checkinSql="";
		if(startDate.equals("")||endDate.equals("")){
			totalCountSql="select count(*) from productcheckin";
			checkinSql="select checksheetid,checkindate,orderid,productid,productname,drawingid,spec,B.createperson,C.user_name createpersonName,status " +
			"from (select A.*,rownum row_num from (select EM.* from productcheckin EM order by checkindate desc,checksheetid desc ) A where rownum<="+(pageSize*pageNow)+" " +
					"order by checkindate desc,checksheetid desc ) B " +
					"left join sysusers C on C.staff_code=B.createperson " +
					"where row_num >="+(pageSize*(pageNow-1)+1)+" order by checkindate desc,checksheetid desc";
		}else{
			totalCountSql="select count(*) from productcheckin where checkindate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			checkinSql="select checksheetid,checkindate,orderid,productid,productname,drawingid,spec,B.createperson,C.user_name createpersonName,status " +
			"from (select A.*,rownum row_num from (select EM.* from productcheckin EM where checkindate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') " +
					"order by checkindate desc,checksheetid desc ) A where rownum<="+(pageSize*pageNow)+" " +
					"order by checkindate desc,checksheetid desc ) B " +
					"left join sysusers C on C.staff_code=B.createperson " +
					"where row_num >="+(pageSize*(pageNow-1)+1)+" order by checkindate desc,checksheetid desc";
		}
		
		
		
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		List<ProductCheckin> resultList=new ArrayList<ProductCheckin>();
		try{
			resultList=Sqlhelper.exeQueryList(checkinSql, null, ProductCheckin.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
		
	}

}
