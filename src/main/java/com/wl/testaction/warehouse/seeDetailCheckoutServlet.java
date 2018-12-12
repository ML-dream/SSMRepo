package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckinDetl;
import com.wl.forms.CheckoutDetl;
import com.wl.tools.Sqlhelper;

public class seeDetailCheckoutServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checksheet_id=request.getParameter("checksheetId");
		String warehouseId=request.getParameter("warehouseId");
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "select count(*) from mycheckout_detl where checksheet_id='"+checksheet_id+"'";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql="select checksheet_id,B.item_id,B.item_name,B.spec,B.unit,B.checkout_num,B.unitprice,B.price,B.stock_id," +
				"B.memo,B.issue_num,B.item_type,alreadypaynum,nopaynum,purductnum,drawingid drawingId,C.item_typedesc itemTypeDesc,D.stock_num stockNum " +
				"from (select A.*,rownum row_num from(select EM.* from mycheckout_detl EM where EM.checksheet_id='"+checksheet_id+"' order by item_id) A " +
				"where rownum<="+(countPerPage*pageNo)+" order by item_id) B " +
				"left join itemtype C on C.item_typeid=B.item_type " +
				"left join stock D on D.item_id=B.item_id and D.warehouse_id='"+warehouseId+"'" +
				"where row_num>="+((pageNo-1)*countPerPage+1)+" order by item_id";
		System.out.println(sql);
		List<CheckoutDetl> resultList=new ArrayList<CheckoutDetl>();
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, CheckoutDetl.class);
			
			
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
