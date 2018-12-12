package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkin;
import com.wl.tools.Sqlhelper;

public class showCheckinServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "select count(*) from mycheckin";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 String sql="select checkin_date,checksheet_id,company_id,checkin_kind,examine,admin,delivery,B.warehouse_id,T.warehouse_name," +
					"B.deptid, C.companyname companyName,D.deptname deptName, E.staff_name examineName,F.staff_Name adminName, T.staff_name deliveryName " +
					"from (select A.*,rownum from(select EM.* from mycheckin EM order by checksheet_id) A where rownum<="+(countPerPage*pageNo)+" order by checksheet_id) B " +
					"left join CUSTOMER C on C.companyid=B.company_id " +
					"left join warehouse T on T.warehouse_id=B.warehouse_id " +
					"left join dept D on D.deptid=B.deptid " +
					"left join EMPLOYEE_INFO E on E.staff_code=B.examine " +
					"left join EMPLOYEE_INFO F on F.staff_code=B.admin " +
					"left join EMPLOYEE_INFO T on T.staff_code=B.delivery " +
					" where rownum>="+(countPerPage*(pageNo-1))+" order by checksheet_id";
		 List<Checkin> resultList = new ArrayList<Checkin>();
		 try{
			 
			 resultList=Sqlhelper.exeQueryList(sql, null, Checkin.class);
			
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 String json = PluSoft.Utils.JSON.Encode(resultList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.getWriter().append(json).flush();
			System.out.println(json);	
		 
		
	}

}
