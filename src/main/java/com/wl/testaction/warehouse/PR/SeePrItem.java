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

public class SeePrItem extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SeePrItem() {
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
//     查看收货记录
		System.out.println(this.getClass().getName());
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String sheetId = request.getParameter("key");
		
		String sqla ="select * from("+
			"select t.itemid,t.itemname,t.spec,t.unit,t.itemtype,t.ussage,t.prnum,t.innum,t.unitprice,t.price,t.warehouseid," +
			"a.item_typedesc itemtypename,b.warehouse_name warehousename,POSHEETID,t.status,rownum rn from PRDETAIL t" +
				" left join itemtype  a on a.item_typeid= t.itemtype" +
				" left join warehouse b on b.warehouse_id = t.warehouseid" +
				" where t.prsheetid='"+sheetId+"' ) " +
				" where  rn>"+min+" and rn <="+max;
		List< PrDetail> lista = new ArrayList<PrDetail>();
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, PrDetail.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sqlb= "select count(*) from (" +
				"select t.itemid from PRDETAIL t where t.prsheetid='"+sheetId+"')";
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
