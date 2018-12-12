package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PrDetail;
import com.wl.tools.Sqlhelper;

public class LoadPurchaseItem extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadPurchaseItem() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		doPost(request, response);
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
// 采购收货，加载订货单下的物料 
		System.out.println(this.getClass().getName());
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String purId = request.getParameter("key");		//订货单单号
		String sqla = "select item_id,item_name,spec,unit, prNum,unitprice,price,b.kind itemType,ussage,C.ITEM_TYPEDESC itemTypeName,poSheetid from ("+
			"select t.item_id,t.item_name,t.spec,t.unit,t.po_num prNum,t.unitprice,t.price,t.kind,t.usage ussage,rownum rn,t.po_sheetid poSheetid from" +
				" POPLAN_DETL t where t.po_sheetid='"+purId+"' order by t.item_id asc) b " +
				"left join itemtype C on C.item_typeid=B.kind"+
						" where  rn>"+min+" and rn <="+max;
		
		List< PrDetail> lista = new ArrayList<PrDetail>();
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, PrDetail.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sqlb = "select count(*) from("+
			"select t.item_id from POPLAN_DETL t where t.po_sheetid='"+purId+"')";
		int count = 0;
		try {
			count = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = PluSoft.Utils.JSON.Encode(lista);
		json = "{\"total\":"+count+",\"data\":"+json+"}";
		System.out.println(json);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json).flush();
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
