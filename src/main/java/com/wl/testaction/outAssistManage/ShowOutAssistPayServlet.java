package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.SupplierPayment;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ShowOutAssistPayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String startDate=ChineseCode.toUTF8(request.getParameter("startDate"));
		String endDate=ChineseCode.toUTF8(request.getParameter("endDate"));
		String companyId=request.getParameter("companyId");
		String totalCountSql="";
		String paySql="";
		String thispaidSql="";
		String hasPaid="";
		if(startDate.equals("")&&endDate.endsWith("")){
			totalCountSql="select count(*) from outAssistpay where companyid='"+companyId+"'";
			paySql="select paydate,thispay thisPaid,qualityFine qualitykk from(select A.*,rownum row_num from(select EM.* from outAssistPay EM where " +
					"companyid='"+companyId+"' order by paydate) A where rownum<="+(pageSize*pageNow)+" order by paydate)B where " +
					"row_num >="+(pageSize*(pageNow-1)+1)+" order by paydate";
			thispaidSql="select cast(sum(thispay)as number(20,3)) from outAssistPay where companyid='"+companyId+"'";
		}else{
			totalCountSql="select count(*) from outAssistpay where companyid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
					"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			paySql="select paydate,thispay thisPaid,qualityFine qualitykk from(select A.*,rownum row_num from(select EM.* from outAssistPay EM where " +
					"companyid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') order by paydate) A where rownum<="+(pageSize*pageNow)+" order by paydate)B where " +
					"row_num >="+(pageSize*(pageNow-1)+1)+" order by paydate";
			thispaidSql="select cast(sum(thispay) as number(20,3)) from outAssistPay where companyid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
		}
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		List<SupplierPayment> resultList=new ArrayList<SupplierPayment>();
		try{
			resultList=Sqlhelper.exeQueryList(paySql, null, SupplierPayment.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			hasPaid=Sqlhelper.exeQueryString(thispaidSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		double haspaid=(hasPaid==null?0:Integer.parseInt(hasPaid));
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+",\"haspaid\":"+haspaid+",}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}



}
