package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.FoHeader;
import com.wl.tools.Sqlhelper;

public class ShowProductMatirialServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowProductMatirialServlet() {
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
		request.setCharacterEncoding("utf-8");
		String orderId=request.getParameter("orderId");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		
		String totalCountSql="";
		String Sql="";
		if(orderId==null){
			orderId="";
		}
		if(orderId.equals("")){
			totalCountSql="select count(*) from foheader";
			Sql="select orderid,productid,productname,issuenum,spec,productnum,matirial,roughsize from " +
			"(select A.*,rownum row_num from(select EM.* from foheader EM order by productid asc) A " +
					"where rownum<="+(pageSize*pageNow)+" order by productid) B " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		}else{
			totalCountSql="select count(*) from foheader where orderid='"+orderId+"'";
			Sql="select orderid,productid,productname,issuenum,spec,productnum,matirial,roughsize from " +
			"(select A.*,rownum row_num from(select EM.* from foheader EM where orderid='"+orderId+"' order by productid asc) A " +
					"where rownum<="+(pageSize*pageNow)+" order by productid) B " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		}
		
		
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		List<FoHeader> resultList=new ArrayList<FoHeader>();
		try{
			resultList=Sqlhelper.exeQueryList(Sql, null, FoHeader.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
	}

}
