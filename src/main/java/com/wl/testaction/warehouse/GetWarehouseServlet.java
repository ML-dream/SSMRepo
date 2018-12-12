package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetWarehouseServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    /*String orderStr = "product_id";*/
	    String orderStr = "warehouse_id";
	   // orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "select count(*) from warehouse";
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String sql="select warehouse_id,warehouse_name,shelf_num,shelf_storey,shelf_column,wh_addr,wh_phone,emp_id from warehouse where warehouse_id " +
		"not in(select warehouse_id from warehouse where rownum<="+(countPerPage*(pageNo-1))+") and rownum<="+countPerPage+" order by "+orderStr;
		System.out.println("sql="+sql);
		List<Warehouse> resultList=new ArrayList<Warehouse>();
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, Warehouse.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
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
