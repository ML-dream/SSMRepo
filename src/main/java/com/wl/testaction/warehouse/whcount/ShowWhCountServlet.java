package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.WhCount;
import com.wl.tools.Sqlhelper;

public class ShowWhCountServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowWhCountServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String totalCountSql="select count(*) from whcount";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String countSql="select countSheetid,countDate,warehouseId,E.warehouse_name warehouseName,operatorId,C.staff_name operatorName,B.deptId,T.deptname deptName,empId,D.staff_name empName from " +
				"(select A.*,rownum row_num from(select EM.* from whcount EM order by countsheetid) A where rownum <="+(pageSize*pageNow)+" order by countsheetid) B " +
				"left join dept T on T.deptid=B.deptid " +
				"left join EMPLOYEE_INFO C on C.staff_code=B.operatorId " +
				"left join EMPLOYEE_INFO D on D.staff_code=B.empId " +
				"left join warehouse E on E.warehouse_id=B.warehouseId " +
				"where row_num >="+(pageSize*(pageNow-1)+1)+"";
		List<WhCount> resultList= new ArrayList<WhCount>();
		try{
			resultList=Sqlhelper.exeQueryList(countSql, null, WhCount.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.print(json);
	}

}
