package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoStatistics;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class StatisticsExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StatisticsExcelServlet() {
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
		int totalCount=0;
		
		String poStartDate=StringUtil.isNullOrEmpty(request.getParameter("poStartDate"))?"":request.getParameter("poStartDate");
		String orderId=StringUtil.isNullOrEmpty(request.getParameter("orderId"))?"":request.getParameter("orderId");
		String companyId=StringUtil.isNullOrEmpty(request.getParameter("companyId"))?"":request.getParameter("companyId");
		
//		String Sql = "";
//		String totalCountSql="";
		List<PoStatistics> poStatisticsList=new ArrayList<PoStatistics>();
		
		String totalCountSql="select count(*) from poplan_detl A " +
			"left join po_plan B on B.po_sheetid=A.po_sheetid " +
			"left join prdetail D on D.posheetid=A.po_sheetid and D.itemid=A.item_id " +
			"left join pr C on C.prsheetid=D.prsheetid " +
			"where to_char(B.postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+poStartDate+"%' and nvl(A.orderid,0) like '"+orderId+"%' and B.customerid like '"+companyId+"%'";
		
		
			try {
				totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		String Sql="select B.poSheetid,B.poStartDate,B.customerId,connector,connectorTel,B.orderid orderId,item_id itemId,item_name itemName,T.companyname customerName," +
			"B.spec,kind,usage,po_num poNum,B.unitprice unitPrice,B.price,D.prsheetid prSheetid,E.isbill isBill,E.payterm payTerm,F.item_typedesc itemTypeDesc from (select A.*,rownum row_num from " +
			"(select EM.po_sheetid poSheetid,to_char(EM.postart_date,'yyyy-MM-dd') poStartDate,EM.customerid customerId,EM.connector,EM.connectortel connectorTel,C.* from po_plan EM " +
			"left join poplan_detl C on C.po_sheetid=EM.po_sheetid where to_char(postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+poStartDate+"%' and nvl(C.orderid,0) like '"+orderId+"%' and customerid like '"+companyId+"%' " +
			"order by EM.po_sheetid ) A ) B " +
			"left join prdetail D on D.posheetid=B.po_sheetid and D.itemid=B.item_id " +
			"left join pr E on E.prsheetid=D.prsheetid " +
			"left join itemtype F on F.item_typeid=B.kind " +
			"left join supplier T on T.companyid=B.customerid ";
		
		try {
			poStatisticsList=Sqlhelper.exeQueryList(Sql, null, PoStatistics.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//导出
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		liebiaoxiang.put("poStartDate", "单据日期");
		liebiaoxiang.put("orderId", "订单号");
		liebiaoxiang.put("customerName", "供应商");
		liebiaoxiang.put("connector", "联系人");
		liebiaoxiang.put("itemId", "物品编号");
		liebiaoxiang.put("itemName", "物品名称");
		liebiaoxiang.put("spec", "规格");
		liebiaoxiang.put("itemTypeDesc", "类型");
		liebiaoxiang.put("usage", "产品用途");
		liebiaoxiang.put("poNum", "数量（个）/重量（kg）");
		liebiaoxiang.put("unitPrice", "单价");
		liebiaoxiang.put("price", "合计");
		
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(5500);
		columnWidth.add(6500);
		columnWidth.add(6500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		columnWidth.add(3500);
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, poStatisticsList);

		
	}

}
