package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PrDetail;
import com.wl.forms.ReturnDetail;
import com.wl.tools.Sqlhelper;

public class seeReServlet extends HttpServlet {

	
	public seeReServlet() {
		super();
	}

	
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
		int pageNow=0;
		int pageSize=20;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String reSheetid=request.getParameter("reSheetid");
		String totalCountSql="select count(*) from redetail where resheetid='"+reSheetid+"'";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String reSql="select resheetid,itemid,itemname,spec,unit,itemtype,renum,unitprice,price,stockid,memo,C.item_typedesc itemTypeDesc from " +
				"(select A.*,rownum row_num from (select EM.* from redetail EM where resheetid='"+reSheetid+"' order by itemid asc) A " +
				"where rownum<="+(pageSize*pageNow)+" order by itemid) B " +
						"left join itemtype C on C .item_typeid=B.itemtype " +
						"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<ReturnDetail> resultList=new ArrayList<ReturnDetail>();
		
		try{
			resultList=Sqlhelper.exeQueryList(reSql, null, ReturnDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
