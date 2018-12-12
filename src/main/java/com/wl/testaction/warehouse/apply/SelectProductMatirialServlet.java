package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class SelectProductMatirialServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectProductMatirialServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String matirial=StringUtil.isNullOrEmpty(request.getParameter("matirial"))?"":request.getParameter("matirial");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		
		String totalCountSql="select count(*) from stock where item_name like '%"+matirial+"%' and warehouse_id like '%y%'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String Sql="select item_id itemId,item_name itemName,spec,stock_num stockNum from(select A.*,rownum row_num " +
					"from(select E.* from stock E where item_name like '%"+matirial+"%' and warehouse_id like '%y%' order by e.item_name asc,e.spec asc) A where rownum<="+pageSize*pageNow+" order by item_name asc,spec asc) B " +
						"where row_num>="+(pageSize*(pageNow-1)+1)+" order  by item_name asc,spec asc  ";
		List<StockInfo> matirialList=new ArrayList<StockInfo>();
		try{
			matirialList=Sqlhelper.exeQueryList(Sql, null, StockInfo.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(matirialList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		System.out.println(json);
		response.getWriter().append(json).flush();
		
		
	}

}
