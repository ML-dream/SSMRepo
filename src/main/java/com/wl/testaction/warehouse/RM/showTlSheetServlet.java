package com.wl.testaction.warehouse.RM;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.LlSheet;
import com.wl.forms.TlSheet;
import com.wl.tools.Sqlhelper;

public class showTlSheetServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=20; 
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "select count(*) from tuiliao";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql="select RMDATE,RMSHEETID,B.WAREHOUSEID,C.warehouse_name warehouseName,EMPID,D.staff_name empName,DEPT," +
				"OPERATORID,F.staff_name operatorName,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME from (select A.*,rownum row_num from(select EM.* from " +
				"tuiliao EM order by rmsheetid) A where rownum<="+(countPerPage*pageNo)+" order by rmsheetid) B " +
				"left join warehouse C on C.warehouse_id=B.warehouseid " +
				"left join EMPLOYEE_INFO D on D.staff_code=B.empid " +
				"left join EMPLOYEE_INFO F on F.staff_code=B.operatorid " +
				"where row_num>="+(countPerPage*(pageNo-1)+1)+" order by rmsheetid";
		List<TlSheet> resultList=new ArrayList<TlSheet>();
		
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, TlSheet.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
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
