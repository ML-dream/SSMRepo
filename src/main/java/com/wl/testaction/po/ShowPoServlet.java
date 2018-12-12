package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Poplan;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ShowPoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
		int pageSize=20;
		int totalCount=0;
		pageNo=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String sheetid=StringUtil.isNullOrEmpty(request.getParameter("sheetid"))?"":request.getParameter("sheetid");
		String date=StringUtil.isNullOrEmpty(request.getParameter("date"))?"":request.getParameter("date");
		String customer=StringUtil.isNullOrEmpty(request.getParameter("customer"))?"":request.getParameter("customer");
		//String orderId=StringUtil.isNullOrEmpty(request.getParameter("orderId"))?"":request.getParameter("orderId");
		String enddate=StringUtil.isNullOrEmpty(request.getParameter("enddate"))?"":request.getParameter("enddate");
		String salesmanId=StringUtil.isNullOrEmpty(request.getParameter("salesmanId"))?"":request.getParameter("salesmanId");
		String drawerId=StringUtil.isNullOrEmpty(request.getParameter("drawerId"))?"":request.getParameter("drawerId");
		String showPoSql="";
		String totalCountSql="";
	
		
		totalCountSql="select count(*) from po_plan where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
		"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
		"and drawerid like '"+drawerId+"%' and customerid like '"+customer+"%'";
		
		showPoSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
		"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername,status from(select A.*,rownum row_num from " +
		"(select EM.* from po_plan EM where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
		"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
		"and drawerid like '"+drawerId+"%' and customerid like '"+customer+"%' order by postart_date desc,po_sheetid desc) A where rownum<="+(pageNo*pageSize)+" order by postart_date desc,po_sheetid desc) B " +
		"left join employee_info C on C.staff_code=B.salesman_id " +
		"left join employee_info D on D.staff_code=B.drawerid " +
		"left join supplier G on G.companyid=B.customerid " +
		"where row_num>="+(pageSize*(pageNo-1)+1)+" order by postart_date desc,po_sheetid desc";
//		if(orderId.equals("")&&customer.equals("")){
//			totalCountSql="select count(*) from po_plan where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//					"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//					"and drawerid like '"+drawerId+"%'";
//			showPoSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
//				"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername from(select A.*,rownum row_num from " +
//				"(select EM.* from po_plan EM where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' order by po_sheetid) A where rownum<="+(pageNo*pageSize)+" order by po_sheetid) B " +
//				"left join employee_info C on C.staff_code=B.salesman_id " +
//				"left join employee_info D on D.staff_code=B.drawerid " +
//				"left join supplier G on G.companyid=B.customerid " +
//				"where row_num>="+(pageSize*(pageNo-1)+1)+"";
//		}else if(!orderId.equals("")&&customer.equals("")){
//			totalCountSql="select count(*) from po_plan where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' and orderid like '"+orderId+"%'";
//			showPoSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
//				"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername from(select A.*,rownum row_num from " +
//				"(select EM.* from po_plan EM where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' and orderid like '"+orderId+"%' order by po_sheetid) A where rownum<="+(pageNo*pageSize)+" order by po_sheetid) B " +
//				"left join employee_info C on C.staff_code=B.salesman_id " +
//				"left join employee_info D on D.staff_code=B.drawerid " +
//				"left join supplier G on G.companyid=B.customerid " +
//				"where row_num>="+(pageSize*(pageNo-1)+1)+"";
//		}else if(orderId.equals("")&&!customer.equals("")){
//			totalCountSql="select count(*) from po_plan where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' and customerid like '"+customer+"%'";
//			showPoSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
//				"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername from(select A.*,rownum row_num from " +
//				"(select EM.* from po_plan EM where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' and customerid like '"+customer+"%' order by po_sheetid) A where rownum<="+(pageNo*pageSize)+" order by po_sheetid) B " +
//				"left join employee_info C on C.staff_code=B.salesman_id " +
//				"left join employee_info D on D.staff_code=B.drawerid " +
//				"left join supplier G on G.companyid=B.customerid " +
//				"where row_num>="+(pageSize*(pageNo-1)+1)+"";
//		}else if(!orderId.equals("")&&!customer.equals("")){
//			totalCountSql="select count(*) from po_plan where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' and customerid like '"+customer+"%' and orderid like '"+orderId+"%'";
//			showPoSql="select PO_SHEETID,CUSTOMERID,B.CONNECTOR,B.CONNECTORTEL,POSTART_DATE,END_DATE,SALESMAN_ID,DRAWERID," +
//				"ORDERID,C.STAFF_NAME salesmanname,D.STAFF_NAME drawername,G.COMPANYNAME customername from(select A.*,rownum row_num from " +
//				"(select EM.* from po_plan EM where po_sheetid like '"+sheetid+"%' and to_char(postart_date,'yyyy-mm-dd,hh24:mi:ss') " +
//				"like '"+date+"%' and to_char(end_date,'yyyy-mm-dd,hh24:mi:ss') like '"+enddate+"%' and salesman_id like '"+salesmanId+"%' " +
//				"and drawerid like '"+drawerId+"%' and customerid like '"+customer+"%' and orderid like '"+orderId+"%' order by po_sheetid) A where rownum<="+(pageNo*pageSize)+" order by po_sheetid) B " +
//				"left join employee_info C on C.staff_code=B.salesman_id " +
//				"left join employee_info D on D.staff_code=B.drawerid " +
//				"left join supplier G on G.companyid=B.customerid " +
//				"where row_num>="+(pageSize*(pageNo-1)+1)+"";
//		}
		
		List<Poplan> resultList=new ArrayList<Poplan>();
		try{
			resultList=Sqlhelper.exeQueryList(showPoSql, null, Poplan.class);
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		 String json = PluSoft.Utils.JSON.Encode(resultList);
		 json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		 //response.setCharacterEncoding("UTF-8");
		 response.getWriter().append(json).flush();
		 System.out.println(json);
	}

}
