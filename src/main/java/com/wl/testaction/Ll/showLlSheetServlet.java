package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.LlSheet;
import com.wl.tools.Sqlhelper;

public class showLlSheetServlet extends HttpServlet {

	
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
	    String totalCountSql = "select count(*) from lingliao";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql="select LLDATE,LL_SHEETID,B.WAREHOUSE_ID,C.warehouse_name warehouseName,B.EMP_ID,D.staff_name empName,DEPT," +
				"OPERATOR_ID,F.staff_name operatorName,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME from (select A.*,rownum row_num from(select EM.* from " +
				"lingliao EM order by lldate desc,ll_sheetid desc) A where rownum<="+(countPerPage*pageNo)+" order by  lldate desc,ll_sheetid desc) B " +
				"left join warehouse C on C.warehouse_id=B.warehouse_id " +
				"left join EMPLOYEE_INFO D on D.staff_code=B.emp_id " +
				"left join EMPLOYEE_INFO F on F.staff_code=B.operator_id " +
				"where row_num>="+(countPerPage*(pageNo-1)+1)+" order by  lldate desc,ll_sheetid desc ";
		List<LlSheet> resultList=new ArrayList<LlSheet>();
		
		try{
			resultList=Sqlhelper.exeQueryList(sql, null, LlSheet.class);
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
	 * m request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
