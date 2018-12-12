package com.wl.testaction.warehouse.customerreturncheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerReturn;
import com.wl.tools.Sqlhelper;

public class ShowCustomerReturnServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowCustomerReturnServlet() {
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
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String totalCountSql="select count(*) from customerreturn";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql="select sheetid,returndate,orderid,B.companyid,C.companyname,B.connector,B.connectortel from " +
				"(select A.*,rownum row_num from(select EM.* from customerreturn EM order by sheetid) A " +
				"where rownum<="+pageNow*pageSize+" order by sheetid) B " +
					"left join customer C on C.companyid=B.companyid " +
					"where row_num >="+(pageSize*(pageNow-1)+1)+"";
		
		List<CustomerReturn> resultList=new ArrayList<CustomerReturn>();
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, CustomerReturn.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
	}

}
