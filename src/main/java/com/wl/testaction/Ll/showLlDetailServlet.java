package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.LlSheetDetail;
import com.wl.tools.Sqlhelper;

public class showLlDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=0;
		int pageSize=20;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String ll_sheetid=request.getParameter("ll_sheetid");
		String totalCountSql="select count(*) from lingliao where ll_sheetid='"+ll_sheetid+"'";
		System.out.println("totalCountSql="+totalCountSql);
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="select item_id,item_name,order_id,issue_num,item_type,spec,unit,ll_num,unitprice,price,stock_id,memo,productname,C.item_typedesc " +
				"from (select A.*, rownum row_num from(select * from lingliao_detl where ll_sheetid='"+ll_sheetid+"' order by item_id) A " +
				"where rownum<='"+(pageSize*pageNow)+"' order by ll_sheetid) B " +
						"left join itemtype C on C.item_typeid=B.item_type " +
						"where row_num>='"+(pageSize*(pageNow-1))+"' ";	
		List<LlSheetDetail> resultList=new ArrayList<LlSheetDetail>();
		System.out.println("sql="+sql);
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, LlSheetDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		
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

		doGet(request,response);
	}

}
