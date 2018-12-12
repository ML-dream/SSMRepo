package com.wl.testaction.warehouse.salesstatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkout;
import com.wl.forms.taxRate;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;

public class SalesStatementExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SalesStatementExcelServlet() {
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
		request.setCharacterEncoding("utf-8");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String companyId=request.getParameter("companyId");
		
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
			
			checkoutSql="select to_char(checkout_date,'yyyy-MM-dd') checkoutDate,A.checksheet_id checksheetId,A.companyid companyid,A.warehouse_id warehouseId,delivery," +
			"B.item_id itemId,B.item_name itemName,B.spec spec,B.checkout_num checkoutNum,nvl(B.unit,'') unit,B.unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,C.Warehouse_Name warehouseName," +
			"D.STAFF_NAME deliveryName,E.companyname companyname,E.connector connector,b.order_id orderId,isreceipted isreceiptedCN from mycheckout A " +
			"left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
					"left join warehouse C on C.warehouse_id=A.warehouse_id " +
					"left join employee_info D on D.staff_code=A.delivery " +
					"left join customer E on E.companyid=A.companyid " +
					"order by A.checkout_date asc,A.checksheet_id asc ";
			
		}else if(!startDate.equals("")&&!endDate.equals("")&&companyId.equals("")){
			checkoutSql="select to_char(checkout_date,'yyyy-MM-dd') checkoutDate,A.checksheet_id checksheetId,A.companyid companyid,A.warehouse_id warehouseId,delivery," +
			"B.item_id itemId,B.item_name itemName,B.spec spec,B.checkout_num checkoutNum,B.unit unit,B.unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,C.Warehouse_Name warehouseName," +
			"D.STAFF_NAME deliveryName,E.companyname companyname,E.connector connector,b.order_id orderId,isreceipted isreceiptedCN  from mycheckout A " +
			"left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
					"left join warehouse C on C.warehouse_id=A.warehouse_id " +
					"left join employee_info D on D.staff_code=A.delivery " +
					"left join customer E on E.companyid=A.companyid " +
					"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') " +
						"order by A.checkout_date asc,A.checksheet_id asc ";
//			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
//			"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
//			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
//			"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector from " +
//			"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
//			"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') "+
//			"order by checkout_date asc,EM.checksheet_id asc ) A " +
//				"where rownum<="+pageSize*pageNow+" order by checkout_date asc,checksheet_id asc ) B " +
//					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
//					"left join warehouse D on D.warehouse_id=B.warehouse_id " +
//					"left join employee_info E on E.staff_code=B.delivery " +
//					"left join customer F on F.companyid=B.companyid " +
//					"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date asc,B.checksheet_id asc ";
			
		}else if(startDate.equals("")&&endDate.equals("")&&!companyId.equals("")){
			checkoutSql="select to_char(checkout_date,'yyyy-MM-dd') checkoutDate,A.checksheet_id checksheetId,A.companyid companyid,A.warehouse_id warehouseId,delivery," +
			"B.item_id itemId,B.item_name itemName,B.spec spec,B.checkout_num checkoutNum,B.unit unit,B.unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,C.Warehouse_Name warehouseName," +
			"D.STAFF_NAME deliveryName,E.companyname companyname,E.connector connector,b.order_id orderId,isreceipted isreceiptedCN  from mycheckout A " +
			"left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
					"left join warehouse C on C.warehouse_id=A.warehouse_id " +
					"left join employee_info D on D.staff_code=A.delivery " +
					"left join customer E on E.companyid=A.companyid " +
					"where a.companyid='"+companyId+"' order by A.checkout_date asc,A.checksheet_id asc ";
//			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
//				"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
//				"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
//				"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector from " +
//				"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
//				"where companyid='"+companyId+"' "+
//				"order by checkout_date asc,EM.checksheet_id asc ) A " +
//				"where rownum<="+pageSize*pageNow+" order by checkout_date asc,checksheet_id asc ) B " +
//				//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
//				"left join warehouse D on D.warehouse_id=B.warehouse_id " +
//				"left join employee_info E on E.staff_code=B.delivery " +
//				"left join customer F on F.companyid=B.companyid " +
//				"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date asc,B.checksheet_id asc ";
		}else if(!startDate.equals("")&&!endDate.equals("")&&!companyId.equals("")){
			checkoutSql="select to_char(checkout_date,'yyyy-MM-dd') checkoutDate,A.checksheet_id checksheetId,A.companyid companyid,A.warehouse_id warehouseId,delivery," +
			"B.item_id itemId,B.item_name itemName,B.spec spec,B.checkout_num checkoutNum,B.unit unit,B.unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
			"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,C.Warehouse_Name warehouseName," +
			"D.STAFF_NAME deliveryName,E.companyname companyname,E.connector connector,b.order_id orderId,isreceipted isreceiptedCN  from mycheckout A " +
			"left join mycheckout_detl B on B.checksheet_id=A.checksheet_id " +
					//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
					"left join warehouse C on C.warehouse_id=A.warehouse_id " +
					"left join employee_info D on D.staff_code=A.delivery " +
					"left join customer E on E.companyid=A.companyid " +
					"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') " +
							"and a.companyid='"+companyId+"' order by A.checkout_date asc,A.checksheet_id asc ";
			
			
			
//			checkoutSql="select checkout_date checkoutDate,B.checksheet_id checksheetId,B.companyid companyid,B.warehouse_id warehouseId,delivery," +
//				"item_id itemId,item_name itemName,spec spec,checkout_num checkoutNum,unit unit,unitprice unitPrice,cast((checkout_num*unitprice/"+rate+") as number(12,2)) price," +
//				"cast((checkout_num*unitprice/"+rate+")*"+valueAddedRate+" as number(12,2)) tax,cast((checkout_num*unitprice) as number(12,2)) totalPrice,D.Warehouse_Name warehouseName," +
//				"E.STAFF_NAME deliveryName,F.companyname companyname,F.connector connector from " +
//				"(select A.*,rownum row_num from(select EM.*,M.item_id,M.item_name,M.spec,M.checkout_num,M.unit,M.unitprice from mycheckout EM left join mycheckout_detl M on M.checksheet_id=EM.checksheet_id " +
//				"where checkout_date between to_date('"+startDate+"','yyyy-MM-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd,hh24:mi:ss') " +
//				"and companyid='"+companyId+"' "+
//				"order by checkout_date asc,EM.checksheet_id asc ) A " +
//				"where rownum<="+pageSize*pageNow+" order by checkout_date asc,checksheet_id asc ) B " +
//				//"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
//				"left join warehouse D on D.warehouse_id=B.warehouse_id " +
//				"left join employee_info E on E.staff_code=B.delivery " +
//				"left join customer F on F.companyid=B.companyid " +
//				"where row_num>="+(pageSize*(pageNow-1)+1)+" order by B.checkout_date asc,B.checksheet_id asc ";
		}
		List<Checkout> resultList=new ArrayList<Checkout>();
		try{
			resultList=Sqlhelper.exeQueryList(checkoutSql, null, Checkout.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		liebiaoxiang.put("checkoutDate", "单据日期");
		liebiaoxiang.put("checksheetId", "单据编号");
		liebiaoxiang.put("orderId", "订单号");
		liebiaoxiang.put("companyname", "客户");
		liebiaoxiang.put("connector", "联系人");
		liebiaoxiang.put("warehouseName", "库房");
		liebiaoxiang.put("deliveryName", "送货人");
		liebiaoxiang.put("itemId", "货品编号");
		liebiaoxiang.put("itemName", "货品名称");
		liebiaoxiang.put("isreceiptedCN", "开具发票");
		liebiaoxiang.put("spec", "规格");
		liebiaoxiang.put("checkoutNum", "数量");
		liebiaoxiang.put("unit", "单位");
		liebiaoxiang.put("unitPrice", "单价");
		liebiaoxiang.put("price", "销售货款");
		liebiaoxiang.put("tax", "销项税额");
		liebiaoxiang.put("totalPrice", "税价合计");
		
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(5500);
		columnWidth.add(6500);
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(3500);
		columnWidth.add(6500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		
		
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, resultList);
		
		
	}

}
