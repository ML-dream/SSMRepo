package com.wl.testaction.warehouse.customerreturncheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerReturnDetail;
import com.wl.tools.Sqlhelper;

public class seeCustomerReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public seeCustomerReturnServlet() {
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
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String sheetId=request.getParameter("sheetId");
		String totalCountSql="select count(*) from customerreturndetail where sheetid='"+sheetId+"'";
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="select itemid,itemname,issuenum,spec,unit,returnnum,unitprice,price,qkprice,returnreason from " +
				"(select A.*,rownum row_num from (select EM.* from customerreturndetail EM where sheetid='"+sheetId+"' order by sheetid) A " +
						"where rownum<="+(pageSize*pageNow)+" order by sheetid) B where row_num >="+(pageSize*(pageNow-1)+1)+"";
		List<CustomerReturnDetail> resultList=new ArrayList<CustomerReturnDetail>();
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, CustomerReturnDetail.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		System.out.println(json);
		response.getWriter().append(json).flush();
		
	}

}
