package com.wl.testaction.machineManage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Machine;
import com.wl.tools.Sqlhelper;

/**
 * Servlet implementation class seclectMachineTime
 */
public class seclectMachineTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int pageNo = 0;
		int countPerPage =0;
		int totalCount = 0;
		String machineId = request.getParameter("equipCode");
		pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
		countPerPage = Integer.parseInt(request.getParameter("pageSize"));
		
		Date day=new Date(new Date().getTime()-24*60*60*1000);    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		/*System.out.println(df.format(day));  */ 
		
		String totalCountSql = "select count(*) from machineinfo_time where machineId ='"+machineId+"'and machineTime>'"+df.format(day)+"'";
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		
		String machineTimeSql="select rownum,b.* from (select a.* from MACHINEINFO_TIME a where machineid  =? and machineTime>? order by machinetime) b  WHERE ROWNUM  BETWEEN ? and ?";
		String params[]= {machineId,df.format(day),String.valueOf(countPerPage*(pageNo-1)+1),String.valueOf(countPerPage*pageNo)};
		
		List<Machine> resultList = new ArrayList<Machine>();
	    try {
			resultList = Sqlhelper.exeQueryList(machineTimeSql, params, Machine.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		/*int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "COMPANYID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	  
	    String customname=StringUtil.isNullOrEmpty(request.getParameter("companyname"))?"":request.getParameter("companyname");
	 
	    String totalCountSql = "select count(*) from customer where istogether='Y' and companyname like '%"+customname+"%'";
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String CustomerSql= "";
	    CustomerSql = 
	    
	    "select D.* from (SELECT ROWNUM ROW_NUM, C.* from (select  A.*,B.NAME typename from CUSTOMER A join companytype B on A.type=b.id " +
	    "WHERE COMPANYNAME LIKE '%"+customname+"%'and istogether='Y' order by companyid) C) D WHERE ROW_NUM" +
	    		" BETWEEN "+(countPerPage*(pageNo-1)+1)+" AND "+(countPerPage*pageNo)+"";
	     
	    List<Customer> customerList = new ArrayList<Customer>();
	    try {
			customerList = Sqlhelper.exeQueryList(CustomerSql, null, Customer.class);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(customerList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);*/
	    
	 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}