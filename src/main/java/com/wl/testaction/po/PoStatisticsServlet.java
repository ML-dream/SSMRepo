package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPayDetl;
import com.wl.forms.PoStatistics;
import com.wl.tools.Sqlhelper;

public class PoStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoStatisticsServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String prSheetid;
		String itemId;
		String orderId=request.getParameter("orderId");
		String date=request.getParameter("date");
		String customerId=request.getParameter("customerId");
		String isbill=request.getParameter("isbill");
		String Sql = "";
		String totalCountSql="";
		List<PoStatistics> poStatistics=new ArrayList<PoStatistics>();
		if(!orderId.equals("")&&!isbill.equals("")){
			
			totalCountSql="select count(*) from poplan_detl A " +
					"left join po_plan B on B.po_sheetid=A.po_sheetid " +
					"left join prdetail D on D.posheetid=A.po_sheetid and D.itemid=A.item_id " +
					"left join pr C on C.prsheetid=D.prsheetid " +
					"where to_char(B.postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and nvl(A.orderid,0) like '"+orderId+"%' and B.customerid like '"+customerId+"%' and C.isbill like '"+isbill+"%'";
			
			Sql="select B.po_sheetid poSheetid,B.postart_date poStartDate,B.customerid customerId,B.connector,B.connectortel connectorTel,B.orderid orderId,C.item_id itemId,C.item_name itemName,T.companyname customerName," +
				"C.spec,C.kind,C.usage,C.po_num poNum,C.unitprice unitPrice,C.price,D.prsheetid prSheetid,E.isbill isBill,E.payterm payTerm,F.item_typedesc itemTypeDesc from (select A.*,rownum row_num from " +
				"(select EM.* from po_plan EM where to_char(postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and orderid like '"+orderId+"%' and customerid like '"+customerId+"%' " +
				"order by po_sheetid ) A where rownum<="+(pageSize*pageNow)+" ) B " +
				"left join poplan_detl C on C.po_sheetid=B.po_sheetid " +
				"left join prdetail D on D.posheetid=C.po_sheetid and D.itemid=C.item_id " +
				"left join pr E on E.prsheetid=D.prsheetid " +
				"left join itemtype F on F.item_typeid=C.kind " +
				"left join supplier T on T.companyid=B.customerid " +
				"where row_num>="+(pageSize*(pageNow-1)+1)+" and E.isbill like '"+isbill+"%'";
		}else if(orderId.equals("")&&!isbill.equals("")){
			totalCountSql="select count(*) from poplan_detl A " +
			"left join po_plan B on B.po_sheetid=A.po_sheetid " +
			"left join prdetail D on D.posheetid=A.po_sheetid and D.itemid=A.item_id " +
			"left join pr C on C.prsheetid=D.prsheetid " +
			"where to_char(B.postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and B.customerid like '"+customerId+"%' and C.isbill like '"+isbill+"%'";
	
			Sql="select B.po_sheetid poSheetid,B.postart_date poStartDate,B.customerid customerId,B.connector,B.connectortel connectorTel,B.orderid orderId,C.item_id itemId,C.item_name itemName,T.companyname customerName," +
			"C.spec,C.kind,C.usage,C.po_num poNum,C.unitprice unitPrice,C.price,D.prsheetid prSheetid,E.isbill isBill,E.payterm payTerm,F.item_typedesc itemTypeDesc from (select A.*,rownum row_num from " +
			"(select EM.* from po_plan EM where to_char(postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and customerid like '"+customerId+"%' " +
			"order by po_sheetid ) A where rownum<="+(pageSize*pageNow)+" ) B " +
			"left join poplan_detl C on C.po_sheetid=B.po_sheetid " +
			"left join prdetail D on D.posheetid=C.po_sheetid and D.itemid=C.item_id " +
			"left join pr E on E.prsheetid=D.prsheetid " +
			"left join itemtype F on F.item_typeid=C.kind " +
			"left join supplier T on T.companyid=B.customerid " +
			"where row_num>="+(pageSize*(pageNow-1)+1)+" and E.isbill like '"+isbill+"%'";
				
		}else if(!orderId.equals("")&&isbill.equals("")){
			totalCountSql="select count(*) from poplan_detl A " +
			"left join po_plan B on B.po_sheetid=A.po_sheetid " +
			"left join prdetail D on D.posheetid=A.po_sheetid and D.itemid=A.item_id " +
			"left join pr C on C.prsheetid=D.prsheetid " +
			"where to_char(B.postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and B.customerid like '"+customerId+"%' and B.orderid like '"+orderId+"%'";
	
			Sql="select B.po_sheetid poSheetid,B.postart_date poStartDate,B.customerid customerId,B.connector,B.connectortel connectorTel,B.orderid orderId,C.item_id itemId,C.item_name itemName,T.companyname customerName," +
			"C.spec,C.kind,C.usage,C.po_num poNum,C.unitprice unitPrice,C.price,D.prsheetid prSheetid,E.isbill isBill,E.payterm payTerm,F.item_typedesc itemTypeDesc from (select A.*,rownum row_num from " +
			"(select EM.* from po_plan EM where to_char(postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and customerid like '"+customerId+"%' and orderid like '"+orderId+"%' " +
			"order by po_sheetid ) A where rownum<="+(pageSize*pageNow)+" ) B " +
			"left join poplan_detl C on C.po_sheetid=B.po_sheetid " +
			"left join prdetail D on D.posheetid=C.po_sheetid and D.itemid=C.item_id " +
			"left join pr E on E.prsheetid=D.prsheetid " +
			"left join itemtype F on F.item_typeid=C.kind " +
			"left join supplier T on T.companyid=B.customerid " +
			"where row_num>="+(pageSize*(pageNow-1)+1)+"";
				
		}else if(orderId.equals("")&&isbill.equals("")){
			totalCountSql="select count(*) from poplan_detl A " +
			"left join po_plan B on B.po_sheetid=A.po_sheetid " +
			"left join prdetail D on D.posheetid=A.po_sheetid and D.itemid=A.item_id " +
			"left join pr C on C.prsheetid=D.prsheetid " +
			"where to_char(B.postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and B.customerid like '"+customerId+"%'";
	
			Sql="select B.po_sheetid poSheetid,B.postart_date poStartDate,B.customerid customerId,B.connector,B.connectortel connectorTel,B.orderid orderId,C.item_id itemId,C.item_name itemName,T.companyname customerName," +
			"C.spec,C.kind,C.usage,C.po_num poNum,C.unitprice unitPrice,C.price,D.prsheetid prSheetid,E.isbill isBill,E.payterm payTerm,F.item_typedesc itemTypeDesc from (select A.*,rownum row_num from " +
			"(select EM.* from po_plan EM where to_char(postart_date,'yyyy-MM-dd,hh24:mi:ss') like '"+date+"%' and customerid like '"+customerId+"%' " +
			"order by po_sheetid ) A where rownum<="+(pageSize*pageNow)+" ) B " +
			"left join poplan_detl C on C.po_sheetid=B.po_sheetid " +
			"left join prdetail D on D.posheetid=C.po_sheetid and D.itemid=C.item_id " +
			"left join pr E on E.prsheetid=D.prsheetid " +
			"left join itemtype F on F.item_typeid=C.kind " +
			"left join supplier T on T.companyid=B.customerid " +
			"where row_num>="+(pageSize*(pageNow-1)+1)+"";
				
		}
		try{
			poStatistics=Sqlhelper.exeQueryList(Sql, null, PoStatistics.class);
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int i=0,len=poStatistics.size();i<len;i++){
			double haspaid=0;
			double nopay=0;
			double price=0;
			PoStatistics poSta=new PoStatistics();
			poSta=poStatistics.get(i);
			price=poSta.getPrice();
			prSheetid=poSta.getPrSheetid();
			itemId=poSta.getItemId();
			String sql="select thispay from popaydetl where PRSHEETID='"+prSheetid+"' and ITEMID='"+itemId+"'";
			List<PoPayDetl> resultList=new ArrayList<PoPayDetl>();
			
			try{
				resultList=Sqlhelper.exeQueryList(sql, null, PoPayDetl.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			for(int j=0,l=resultList.size();j<l;j++){
				PoPayDetl popay=new PoPayDetl();
				popay=resultList.get(j);
				haspaid+=popay.getThispay();	
			}	
			nopay=price-haspaid;
			poSta.setHasPaid(haspaid);
			poSta.setNopay(nopay);
		}
		
		String json=PluSoft.Utils.JSON.Encode(poStatistics);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		System.out.println(json);
		out.append(json).flush();
		
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
