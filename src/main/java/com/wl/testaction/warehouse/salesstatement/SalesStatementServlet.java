package com.wl.testaction.warehouse.salesstatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkout;
import com.wl.forms.taxRate;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class SalesStatementServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SalesStatementServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String startDate=StringUtil.isNullOrEmpty(request.getParameter("startDate"))?"":request.getParameter("startDate");
		String endDate=StringUtil.isNullOrEmpty(request.getParameter("endDate"))?"":request.getParameter("endDate");
		String companyId=StringUtil.isNullOrEmpty(request.getParameter("companyId"))?"":request.getParameter("companyId");
		
		String totalCountSql="";
		String checkoutSql="";
		
		String rateSql="select valueaddedrate,rate from taxrate";
		taxRate taxRateBean=new taxRate();
		try{
			taxRateBean=Sqlhelper.exeQueryBean(rateSql, null, taxRate.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		double valueAddedRate=taxRateBean.getValueAddedRate();
		double rate=taxRateBean.getRate();
		
		
		
		if(startDate.equals("")&&endDate.equals("")&&companyId.equals("")){
			totalCountSql="select count(*) from mycheckout A left join mycheckout_detl B on B.checksheet_id=A.checksheet_id";
			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
			"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,orderId,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
			"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector,isreceipted from " +
			"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice,M.order_id orderId,m.isreceipted from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id "+
			"order by checkout_date desc,EM.checksheet_id asc ) A " +
				"where rownum<="+pageSize*pageNow+" order by checkout_date desc,checksheet_id asc ) B " +
					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
					"left join warehouse D on D.warehouse_id=B.warehouse_id " +
					"left join employee_info E on E.staff_code=B.delivery " +
					"left join customer F on F.companyid=B.companyid " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date desc,B.checksheet_id asc ";
			
		}else if(!startDate.equals("")&&!endDate.equals("")&&companyId.equals("")){
			totalCountSql="select count(*) from mycheckout A left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
					"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss')";
			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
			"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,orderId,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
			"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector,isreceipted from " +
			"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice,M.order_id orderId,isreceipted from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
			"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') "+
			"order by checkout_date desc,EM.checksheet_id asc ) A " +
				"where rownum<="+pageSize*pageNow+" order by checkout_date desc,checksheet_id asc ) B " +
					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
					"left join warehouse D on D.warehouse_id=B.warehouse_id " +
					"left join employee_info E on E.staff_code=B.delivery " +
					"left join customer F on F.companyid=B.companyid " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date desc,B.checksheet_id asc ";
			
		}else if(startDate.equals("")&&endDate.equals("")&&!companyId.equals("")){
			totalCountSql="select count(*) from mycheckout A left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
				"where companyid='"+companyId+"'";
			
			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
				"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,orderId,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
				"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
				"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector,isreceipted from " +
				"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice,M.order_id orderId,isreceipted from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
				"where companyid='"+companyId+"' "+
				"order by checkout_date desc,EM.checksheet_id asc ) A " +
				"where rownum<="+pageSize*pageNow+" order by checkout_date desc,checksheet_id asc ) B " +
				//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
				"left join warehouse D on D.warehouse_id=B.warehouse_id " +
				"left join employee_info E on E.staff_code=B.delivery " +
				"left join customer F on F.companyid=B.companyid " +
				"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date desc,B.checksheet_id asc ";
		}else if(!startDate.equals("")&&!endDate.equals("")&&!companyId.equals("")){
			totalCountSql="select count(*) from mycheckout A left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
				"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') " +
					"and companyid ='"+companyId+"'";
			
			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
				"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,orderId,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
				"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
				"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector,isreceipted from " +
				"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice,M.order_id orderId,isreceipted from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
				"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') " +
				"and companyid='"+companyId+"' "+
				"order by checkout_date desc,EM.checksheet_id asc ) A " +
				"where rownum<="+pageSize*pageNow+" order by checkout_date desc,checksheet_id asc ) B " +
				//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
				"left join warehouse D on D.warehouse_id=B.warehouse_id " +
				"left join employee_info E on E.staff_code=B.delivery " +
				"left join customer F on F.companyid=B.companyid " +
				"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date desc,B.checksheet_id asc ";
		}
		
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
//		checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
//				"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
//				"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
//				"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector from " +
//				"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
//				"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') " +
//				"and companyid ='"+companyId+"' order by checkout_date asc,EM.checksheet_id asc ) A " +
//					"where rownum<="+pageSize*pageNow+" order by checkout_date asc,checksheet_id asc ) B " +
//						//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
//						"left join warehouse D on D.warehouse_id=B.warehouse_id " +
//						"left join employee_info E on E.staff_code=B.delivery " +
//						"left join customer F on F.companyid=B.companyid " +
//						"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date asc,B.checksheet_id asc ";
//		
		List<Checkout> resultList=new ArrayList<Checkout>();
		try{
			resultList=Sqlhelper.exeQueryList(checkoutSql, null, Checkout.class);
			String json = PluSoft.Utils.JSON.Encode(resultList); 
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.getWriter().append(json).flush();
			System.out.println(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
