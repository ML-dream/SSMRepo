package com.wl.testaction.warehouse.PR;

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
import com.wl.forms.PrDetail;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class PrStatisticsExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrStatisticsExcelServlet() {
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
		String startDate=StringUtil.isNullOrEmpty(request.getParameter("startDate"))?"":request.getParameter("startDate");
		String endDate=StringUtil.isNullOrEmpty(request.getParameter("endDate"))?"":request.getParameter("endDate");
		String companyId=StringUtil.isNullOrEmpty(request.getParameter("companyId"))?"":request.getParameter("companyId");
		String payMethod=(StringUtil.isNullOrEmpty(request.getParameter("payMethod"))?"":request.getParameter("payMethod")).toString();
		String totalCountSql="";
		String prSql="";
		String viewSql="";
		
		
		if(payMethod.equals("")){
			viewSql="create or replace view prdetailView as select M.* from prdetail M ";
			
		}else{
			viewSql="create or replace view prdetailView as select M.* from prdetail M where paymethod='"+payMethod+"'";
		}
		
		try {
			Sqlhelper.executeUpdate(viewSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		
		if(startDate.equals("")&&endDate.equals("")&&companyId.equals("")){
//			prSql="select prdate,prsheetid,customerid,C.companyname customerName,B.connector,itemid,itemname,spec,prnum,unitprice,price,unit," +
//					"warehouseid,D.warehouse_Name,itemtype,ussage,stockid,paymethod from";
			
			
			prSql="select to_char(prdate,'yyyy-MM-dd') prDate,prsheetid,customerid,C.companyname customerName,B.connector,itemid,itemname,spec,innum,unitprice,price,unit," +
			"          warehouseid,D.warehouse_Name,itemtype,ussage,stockid,paymethod,G.paymethodtext from (select A.*,rownum rn from " +
			"          (select E.prdate,E.prsheetid pr_sheetid,E.customerid,E.connector,M.* from pr E left join prdetailView M on M.prsheetid=E.prsheetid " +
			"          where status='1' order by prdate asc,E.prsheetid asc) A order by prdate asc,prsheetid asc) B " +
			"             left join supplier C on C.companyid=B.customerid " +
			"             left join warehouse D on D.warehouse_id=B.warehouseid " +
			"             left join itemtype F on F.item_typeid=B.itemtype " +
			"			  left join supplierpaymethod G on G.paymethodid=B.paymethod";
		}else if(!startDate.equals("")&&!endDate.equals("")&&companyId.equals("")){

			prSql="select to_char(prdate,'yyyy-MM-dd') prDate,prsheetid,customerid,C.companyname customerName,B.connector,itemid,itemname,spec,innum,unitprice,price,unit," +
			"          warehouseid,D.warehouse_Name,itemtype,ussage,stockid,paymethod,G.paymethodtext from (select A.*,rownum rn from " +
			"          (select E.prdate,E.prsheetid pr_sheetid,E.customerid,E.connector,M.* from pr E left join prdetailView M on M.prsheetid=E.prsheetid " +
			"          where status='1' and prdate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') " +
			"		   order by prdate asc,E.prsheetid asc) A order by prdate asc,prsheetid asc) B " +
			"             left join supplier C on C.companyid=B.customerid " +
			"             left join warehouse D on D.warehouse_id=B.warehouseid " +
			"             left join itemtype F on F.item_typeid=B.itemtype " +
			"			  left join supplierpaymethod G on G.paymethodid=B.paymethod";
		
		}else if(startDate.equals("")&&endDate.equals("")&&!companyId.equals("")){

			prSql="select to_char(prdate,'yyyy-MM-dd') prDate,prsheetid,customerid,C.companyname customerName,B.connector,itemid,itemname,spec,innum,unitprice,price,unit," +
			"          warehouseid,D.warehouse_Name,itemtype,ussage,stockid,paymethod,G.paymethodtext from (select A.*,rownum rn from " +
			"          (select E.prdate,E.prsheetid pr_sheetid,E.customerid,E.connector,M.* from pr E left join prdetailView M on M.prsheetid=E.prsheetid " +
			"          where status='1' and E.customerid='"+companyId+"' order by prdate asc,E.prsheetid asc) A order by prdate asc,prsheetid asc) B " +
			"             left join supplier C on C.companyid=B.customerid " +
			"             left join warehouse D on D.warehouse_id=B.warehouseid " +
			"             left join itemtype F on F.item_typeid=B.itemtype " +
			"			  left join supplierpaymethod G on G.paymethodid=B.paymethod";
		}else if(!startDate.equals("")&&!endDate.equals("")&&!companyId.equals("")){

			prSql="select to_char(prdate,'yyyy-MM-dd') prDate,prsheetid,customerid,C.companyname customerName,B.connector,itemid,itemname,spec,innum,unitprice,price,unit," +
			"          warehouseid,D.warehouse_Name,itemtype,ussage,stockid,paymethod,G.paymethodtext from (select A.*,rownum rn from " +
			"          (select E.prdate,E.prsheetid pr_sheetid,E.customerid,E.connector,M.* from pr E left join prdetailView M on M.prsheetid=E.prsheetid " +
			"          where status='1' and prdate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')  and customerid='"+companyId+"' " +
			"		   order by prdate asc,E.prsheetid asc) A order by prdate asc,prsheetid asc) B " +
			"             left join supplier C on C.companyid=B.customerid " +
			"             left join warehouse D on D.warehouse_id=B.warehouseid " +
			"             left join itemtype F on F.item_typeid=B.itemtype " +
			"			  left join supplierpaymethod G on G.paymethodid=B.paymethod";
		}
		
	
		
		List<PrDetail> resultList=new ArrayList<PrDetail>();
		try{
			resultList=Sqlhelper.exeQueryList(prSql, null, PrDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		liebiaoxiang.put("prDate", "收货日期");
		liebiaoxiang.put("prSheetid", "收货单号");
		liebiaoxiang.put("customerName", "送货单位");
		liebiaoxiang.put("connector", "联系人");
		liebiaoxiang.put("paymethodText", "付款方式");
//		liebiaoxiang.put("warehouseName", "库房");
//		liebiaoxiang.put("deliveryName", "送货人");
		liebiaoxiang.put("itemId", "货品编号");
		liebiaoxiang.put("itemName", "货品名称");
		liebiaoxiang.put("spec", "规格");
		liebiaoxiang.put("inNum", "收货数量");
		liebiaoxiang.put("unitPrice", "单价");
		liebiaoxiang.put("price", "总价");
		liebiaoxiang.put("unit", "单位");
		
//		liebiaoxiang.put("tax", "销项税额");
//		liebiaoxiang.put("totalPrice", "税价合计");
		
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
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
//		columnWidth.add(3500);
//		columnWidth.add(3500);
//		columnWidth.add(3500);
		
		
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, resultList);
		
	}

}
