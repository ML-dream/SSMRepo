package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoDetail;
import com.wl.tools.Sqlhelper;

public class ShowPoDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=0;
		int pageSize=20;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String po_sheetid=request.getParameter("po_sheetid");
		String totalCountSql="select count(*) from poplan_detl where po_sheetid='"+po_sheetid+"'";
//		2017-2-28 xiem
		String sumPrice = "";
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		"select PO_SHEETID,ITEM_ID,ITEM_NAME,SPEC,UNIT,PO_NUM,UNITPRICE,PRICE,MEMO,KIND,USAGE,ORDERID,C.ITEM_TYPEDESC itemType " +
		String poSql="select PO_SHEETID,ITEM_ID,ITEM_NAME,SPEC,UNIT,PO_NUM,UNITPRICE,cast((PO_NUM*UNITPRICE) AS NUMBER(10,2)) PRICE,MEMO,KIND,USAGE,ORDERID,C.ITEM_TYPEDESC itemType " +
				"from(select A.*,rownum row_num from(select EM.* from poplan_detl EM where po_sheetid='"+po_sheetid+"' order by po_sheetid ) A " +
				"where rownum<="+(pageSize*pageNow)+" order by po_sheetid) B " +
				"left join itemtype C on C.item_typeid=B.kind where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<PoDetail> resultList=new ArrayList<PoDetail>();
		try{
			resultList=Sqlhelper.exeQueryList(poSql, null, PoDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		订货单总价  xiem
		String sqla ="select sum(em.unitprice*em.po_num) sumPrice from poplan_detl EM where po_sheetid='"+po_sheetid+"'";
		try {
			sumPrice=Sqlhelper.exeQueryString(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"sumPrice\":"+sumPrice+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
