package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerAssist;
import com.wl.tools.Sqlhelper;

public class showCustomerCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showCustomerCheckinServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String totalCountSql="select count(*) from customercheckin";
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String customerSql="select checkindate,checksheetid,orderid,customerid,customername,itemid,itemname," +
				"itemtypeid,spec,checkinnum,price,warehouseid,stockid,lot,qualityid,memo,deliveryid,examineid,whstaffid," +
				"C.item_typedesc itemTypeDesc,D.warehouse_name warehouseName,E.staff_name deliveryName,F.staff_name examineName," +
				"T.staff_name whstaffName from(select A.*,rownum from(select EM.* from customercheckin EM order by checksheetid ) A " +
				"where rownum<="+pageNow*pageSize+" order by checksheetid) B " +
				"left join itemtype C on C.item_typeid=B.itemtypeid " +
				"left join warehouse D on D.warehouse_id=B.warehouseid " +
				"left join employee_info E on E.staff_code=B.deliveryid " +
				"left join employee_info F on F.staff_code=B.examineid " +
				"left join employee_info T on T.staff_code=B.whstaffid " +
				"where rownum >="+(pageSize*(pageNow-1))+"";
		
		List<CustomerAssist> resultList=new ArrayList<CustomerAssist>();
		try{
			resultList=Sqlhelper.exeQueryList(customerSql, null, CustomerAssist.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
