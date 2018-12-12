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
import com.wl.forms.Checkout;
import com.wl.tools.Sqlhelper;

public class showCheckoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "select count(*) from mycheckout where status>=0 ";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 String sql="select CHECKOUT_DATE,CHECKSHEET_ID,B.COMPANYID,F.COMPANYNAME,F.CONNECTOR,F.CONNECTORTEL,B.WAREHOUSE_ID,C.WAREHOUSE_NAME warehouseName," +
		 		"DELIVERY,D.staff_name deliveryName,OPERATOR,E.staff_name operatorName,ORDER_ID,B.CREATEPERSON,B.CREATETIME,B.CHANGEPERSON,B.CHANGETIME,STATUS from " +
		 		"(select A.*,rownum row_num from (select EM.* from mycheckout EM where status>=0 order by checkout_date desc,checksheet_id desc) A where rownum<="+(countPerPage*pageNo)+" order by checkout_date desc,checksheet_id desc) B " +
		 		"left join warehouse C on C.warehouse_id=B.warehouse_id " +
		 		"left join EMPLOYEE_INFO D on D.staff_code=B.delivery " +
		 		"left join EMPLOYEE_INFO E on E.staff_code=B.operator " +
		 		"left join CUSTOMER F on F.companyid=B.companyid " +
		 		"where row_num>="+(countPerPage*(pageNo-1)+1)+" order by checkout_date desc,checksheet_id desc";
		 	//ResultSet rs=null;
		 List<Checkout> resultList = new ArrayList<Checkout>();
		 try{
			//rs=Sqlhelper.executeQuery(sql,null);
			
			resultList=Sqlhelper.exeQueryList(sql, null, Checkout.class);
			String json = PluSoft.Utils.JSON.Encode(resultList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.getWriter().append(json).flush();
			System.out.println(json);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
			
		 
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request,response);
	}

}
