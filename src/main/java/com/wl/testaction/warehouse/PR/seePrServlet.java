package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoDetail;
import com.wl.forms.PrDetail;
import com.wl.tools.Sqlhelper;

public class seePrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public seePrServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=0;
		int pageSize=20;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String prSheetid=request.getParameter("prSheetid");
		String totalCountSql="select count(*) from prdetail where prsheetid='"+prSheetid+"'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String prSql="select POSHEETID,ITEMID,ITEMNAME,SPEC,UNIT,ITEMTYPE,USSAGE,PRNUM,UNITPRICE,PRICE,TAXRATE,TAX,STOCKID,MEMO,C.ITEM_TYPEDESC itemTypeName " +
		"from(select A.*,rownum row_num from(select EM.* from prdetail EM where prsheetid='"+prSheetid+"' order by prsheetid ) A " +
		"where rownum<="+(pageSize*pageNow)+" order by prsheetid) B left join itemtype C on C.item_typeid=B.itemtype where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<PrDetail> resultList=new ArrayList<PrDetail>();
		
		try{
			resultList=Sqlhelper.exeQueryList(prSql, null, PrDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
		
	}

}
